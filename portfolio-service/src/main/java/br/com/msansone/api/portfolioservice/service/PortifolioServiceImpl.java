package br.com.msansone.api.portfolioservice.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import br.com.msansone.api.portfolioservice.model.rest.StockWebValResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.msansone.api.portfolioservice.client.StockDbClient;
import br.com.msansone.api.portfolioservice.client.StockWebClient;
import br.com.msansone.api.portfolioservice.model.Stock;
import br.com.msansone.api.portfolioservice.model.Transaction;
import br.com.msansone.api.portfolioservice.model.rest.TransactionAddRequest;

@Service
public class PortifolioServiceImpl implements PortifolioService{

	@Autowired
	StockDbClient stockDbClient;
	
	
	@Autowired
	StockWebClient stockWebClient;
	
	@Override
	public List<Transaction> getAllTransactions() {
		ResponseEntity<List<Transaction>> ret = stockDbClient.getAllTransaction();
		
		return ret.getBody();
	}

	@Override
	public Transaction addTransaction(TransactionAddRequest request) {

		Logger LOG = Logger.getLogger("addTransaction");

		LOG.info("Iniciando Add transação...");
		Transaction transaction = new Transaction();
		
		String stockCode = request.getCode();
		Long stockQtd = request.getQuantity();

		// get Stock with code
		Stock stock = getStockInfoInWS(stockCode);


		//Stock stock = responseCode.getBody();
		LOG.info("Stock readed: "+ stock.toString());

		BigDecimal stockUnitaryValue=stock.getLastValue();
		LOG.info("data atual: "+ LocalDateTime.now());
		LOG.info("última atualização : "+ stock.getDateLoastvalue());

		// if stock value stored is old, reload value from web
		if (stock.getDateLoastvalue()==null || LocalDateTime.now().minusMinutes(5).isAfter(stock.getDateLoastvalue())){
			LOG.info("getting last stock value from web...");
			// get stockUnitaryValue from web
			ResponseEntity<StockWebValResponse> response = stockWebClient.getStockWebValue(stockCode);
			//stockUnitaryValue = response.getBody().getValOpen();
			StockWebValResponse body = response.getBody();

			LOG.info("body = "+body.toString());

			if (body.getError()!=null){
				throw  new RuntimeException(body.getCode()+" - "+body.getError().getMessage());
			}
			stockUnitaryValue=body.getValOpen();
			LOG.info("new value from web : "+stockUnitaryValue.toString());
		}

		//update stock value
		stock.setLastValue(stockUnitaryValue);
		stock.setDateLoastvalue(LocalDateTime.now());
		ResponseEntity<Stock> respStockUpdt = stockDbClient.updateStocke(stock.getId(), stock);

		// set transaction
		transaction.setStock(stock);
		transaction.setDate(LocalDate.now());
		transaction.setStockQuantity(stockQtd);
		transaction.setStockUnitValue(stockUnitaryValue);
		transaction.setTransactionType("C");
		transaction.setTransactionValue(stockUnitaryValue.multiply(new BigDecimal(stockQtd)));
		
		// add transaction
		ResponseEntity<Transaction> responseTran = stockDbClient.addTransaction(transaction);
		transaction=responseTran.getBody();
		
		return transaction;
	}


	@HystrixCommand( fallbackMethod = "getStockInfoInWSFallBack")
	private Stock getStockInfoInWS(String stockCode) {
		Stock responseCode = stockDbClient.getStockByCode(stockCode).getBody();
		return responseCode;
	}

	private Stock getStockInfoInWSFallBack(String stockCode) {
		Stock responseCode = new Stock();
		responseCode.setCode(stockCode);
		responseCode.setLastValue(new BigDecimal(-1));
		return responseCode;
	}

}
