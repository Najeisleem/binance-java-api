package com.binance.api.client.impl;

import static com.binance.api.client.impl.ApiServiceGenerator.executeSync;
import static java.lang.System.currentTimeMillis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.config.BinanceApiConfig;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.ApiRestriction;
import com.binance.api.client.domain.account.DepositAddress;
import com.binance.api.client.domain.account.DepositHistory;
import com.binance.api.client.domain.account.DustTransferResponse;
import com.binance.api.client.domain.account.MasterDepositHistory;
import com.binance.api.client.domain.account.MasterWithdrawHistory;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.OcoOrderResponse;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.SubAccount;
import com.binance.api.client.domain.account.Trade;
import com.binance.api.client.domain.account.TradeHistoryItem;
import com.binance.api.client.domain.account.TransferHistory;
import com.binance.api.client.domain.account.WithdrawHistory;
import com.binance.api.client.domain.account.WithdrawResult;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.CancelOrderResponse;
import com.binance.api.client.domain.account.request.OcoOrderStatusRequest;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;
import com.binance.api.client.domain.account.request.SubAccountTransfer;
import com.binance.api.client.domain.general.Asset;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.market.AggTrade;
import com.binance.api.client.domain.market.BookTicker;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;

import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Implementation of Binance's REST API using Retrofit with synchronous/blocking method calls.
 */
public class BinanceApiRestClientImpl implements BinanceApiRestClient {

  private final BinanceApiService binanceApiService;

  public BinanceApiRestClientImpl(String apiKey, String secret) {
    binanceApiService = ApiServiceGenerator.createService(BinanceApiService.class, apiKey, secret);
  }

  // General endpoints

  @Override
  public void ping() {
    executeSync(binanceApiService.ping());
  }

  @Override
  public Long getServerTime() {
    return executeSync(binanceApiService.getServerTime()).getServerTime();
  }

  @Override
  public ExchangeInfo getExchangeInfo() {
    return executeSync(binanceApiService.getExchangeInfo());
  }

  @Override
  public List<Asset> getAllAssets() {
    return executeSync(binanceApiService.getAllAssets(BinanceApiConfig.getAssetInfoApiBaseUrl() + "assetWithdraw/getAllAsset.html"));
  }

  // Market Data endpoints

  @Override
  public OrderBook getOrderBook(String symbol, Integer limit) {
    return executeSync(binanceApiService.getOrderBook(symbol, limit));
  }

  @Override
  public List<TradeHistoryItem> getTrades(String symbol, Integer limit) {
    return executeSync(binanceApiService.getTrades(symbol, limit));
  }

  @Override
  public List<TradeHistoryItem> getHistoricalTrades(String symbol, Integer limit, Long fromId) {
    return executeSync(binanceApiService.getHistoricalTrades(symbol, limit, fromId));
  }

  @Override
  public List<AggTrade> getAggTrades(String symbol, String fromId, Integer limit, Long startTime, Long endTime) {
    return executeSync(binanceApiService.getAggTrades(symbol, fromId, limit, startTime, endTime));
  }

  @Override
  public List<AggTrade> getAggTrades(String symbol) {
    return getAggTrades(symbol, null, null, null, null);
  }

  @Override
  public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime) {
    return executeSync(binanceApiService.getCandlestickBars(symbol, interval.getIntervalId(), limit, startTime, endTime));
  }

  @Override
  public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval) {
    return getCandlestickBars(symbol, interval, null, null, null);
  }

  @Override
  public TickerStatistics get24HrPriceStatistics(String symbol) {
    return executeSync(binanceApiService.get24HrPriceStatistics(symbol));
  }

  @Override
  public List<TickerStatistics> getAll24HrPriceStatistics() {
	return 	executeSync(binanceApiService.getAll24HrPriceStatistics());
  }

  @Override
  public TickerPrice getPrice(String symbol) {
	  return executeSync(binanceApiService.getLatestPrice(symbol));
  }

  @Override
  public List<TickerPrice> getAllPrices() {
    return executeSync(binanceApiService.getLatestPrices());
  }

  @Override
  public List<BookTicker> getBookTickers() {
    return executeSync(binanceApiService.getBookTickers());
  }

  @Override
  public NewOrderResponse newOrder(NewOrder order) {
    if (order.getType() == OrderType.OCO) {
        throw new IllegalArgumentException("Please use newOcoOrder instead");
    }
    final Call<NewOrderResponse> call;
    if (order.getQuoteOrderQty() == null) {
      call = binanceApiService.newOrder(order.getSymbol(), order.getSide(), order.getType(),
          order.getTimeInForce(), order.getQuantity(), order.getPrice(),
          order.getNewClientOrderId(), order.getStopPrice(), order.getIcebergQty(), order.getNewOrderRespType(),
          order.getRecvWindow(), order.getTimestamp());
    } else {
      call = binanceApiService.newOrderQuoteQty(order.getSymbol(), order.getSide(), order.getType(),
          order.getTimeInForce(), order.getQuoteOrderQty(), order.getPrice(),
          order.getNewClientOrderId(), order.getStopPrice(), order.getIcebergQty(), order.getNewOrderRespType(),
          order.getRecvWindow(), order.getTimestamp());
    }
    return executeSync(call);
  }

  @Override
  public OcoOrderResponse newOcoOrder(NewOrder order) {
      return executeSync(binanceApiService.newOcoOrder(order.getSymbol(), order.getNewClientOrderId(), order.getSide(), order.getQuantity(), order.getLimitClientOrderId(),
              order.getPrice(), order.getIcebergQty(), order.getStopClientOrderId(), order.getStopPrice(), order.getStopLimitPrice(), TimeInForce.GTC,
              order.getNewOrderRespType(), order.getRecvWindow(), order.getTimestamp()));
  }

  @Override
  public void newOrderTest(NewOrder order) {
    if (order.getType() == OrderType.OCO) {
        executeSync(binanceApiService.newOcoOrderTest(order.getSymbol(), order.getNewClientOrderId(), order.getSide(), order.getQuantity(), order.getLimitClientOrderId(),
                order.getPrice(), order.getIcebergQty(), order.getStopClientOrderId(), order.getStopPrice(), order.getStopLimitPrice(), TimeInForce.GTC,
                order.getNewOrderRespType(), order.getRecvWindow(), order.getTimestamp()));
    } else {
        executeSync(binanceApiService.newOrderTest(order.getSymbol(), order.getSide(), order.getType(),
                order.getTimeInForce(), order.getQuantity(), order.getPrice(), order.getNewClientOrderId(), order.getStopPrice(),
                order.getIcebergQty(), order.getNewOrderRespType(), order.getRecvWindow(), order.getTimestamp()));
    }
  }

  // Account endpoints

  @Override
  public Order getOrderStatus(OrderStatusRequest orderStatusRequest) {
    return executeSync(binanceApiService.getOrderStatus(orderStatusRequest.getSymbol(),
        orderStatusRequest.getOrderId(), orderStatusRequest.getOrigClientOrderId(),
        orderStatusRequest.getRecvWindow(), orderStatusRequest.getTimestamp()));
  }

  @Override
  public OcoOrderResponse getOcoOrderStatus(OcoOrderStatusRequest statusRequest) {
    return executeSync(binanceApiService.getOcoOrderStatus(statusRequest.getOrderListId(), statusRequest.getOrigClientOrderId(),
        statusRequest.getRecvWindow(), statusRequest.getTimestamp()));
  }

  @Override
  public CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
    return executeSync(binanceApiService.cancelOrder(cancelOrderRequest.getSymbol(),
        cancelOrderRequest.getOrderId(), cancelOrderRequest.getOrigClientOrderId(), cancelOrderRequest.getNewClientOrderId(),
        cancelOrderRequest.getRecvWindow(), cancelOrderRequest.getTimestamp()));
  }

  @Override
  public OcoOrderResponse cancelOcoOrder(CancelOrderRequest cancelOrderRequest) {
    return executeSync(binanceApiService.cancelOcoOrder(cancelOrderRequest.getSymbol(),
        cancelOrderRequest.getOrderId(), cancelOrderRequest.getOrigClientOrderId(), cancelOrderRequest.getNewClientOrderId(),
        cancelOrderRequest.getRecvWindow(), cancelOrderRequest.getTimestamp()));
  }

  @Override
  public List<Order> getOpenOrders(OrderRequest orderRequest) {
    return executeSync(binanceApiService.getOpenOrders(orderRequest.getSymbol(), orderRequest.getRecvWindow(), orderRequest.getTimestamp()));
  }

  @Override
  public List<Order> getAllOrders(AllOrdersRequest orderRequest) {
    return executeSync(binanceApiService.getAllOrders(orderRequest.getSymbol(),
        orderRequest.getOrderId(), orderRequest.getLimit(),
        orderRequest.getRecvWindow(), orderRequest.getTimestamp()));
  }

  @Override
  public Account getAccount(Long recvWindow, Long timestamp) {
    return executeSync(binanceApiService.getAccount(recvWindow, timestamp));
  }

  @Override
  public Account getAccount() {
    return getAccount(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
  }

  @Override
  public List<Trade> getMyTrades(String symbol, Integer limit, Long fromId, Long recvWindow, Long timestamp) {
    return executeSync(binanceApiService.getMyTrades(symbol, limit, fromId, recvWindow, timestamp));
  }

  @Override
  public List<Trade> getMyTrades(String symbol, Integer limit) {
    return getMyTrades(symbol, limit, null, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
  }

  @Override
  public List<Trade> getMyTrades(String symbol) {
    return getMyTrades(symbol, null, null, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
  }


  @Override
  public List<Trade> getMyTrades(String symbol, Long fromId) {
    return getMyTrades(symbol, null, fromId, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW,
        System.currentTimeMillis());
  }

  @Override
  public WithdrawResult withdraw(String asset, String address, String amount, String name, String addressTag) {
    return executeSync(binanceApiService.withdraw(asset, address, amount, name, addressTag, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
  }

  @Override
  public List<MasterDepositHistory> getDepositHistory(Integer status , Long startTime, Long endTime) {
    return executeSync(binanceApiService.getDepositHistory(status, startTime ,endTime , System.currentTimeMillis()));
  }

  @Override
  public List<MasterWithdrawHistory> getWithdrawHistory(Integer status , Long startTime, Long endTime) {
    return executeSync(binanceApiService.getWithdrawHistory(status, startTime ,endTime , System.currentTimeMillis()));
  }

  @Override
  public List<SubAccountTransfer> getSubAccountTransfers(Integer type, Long startTime, Long endTime) {
    return executeSync(binanceApiService.getSubAccountTransfers(type,startTime,endTime, System.currentTimeMillis()));
  }
  
  @Override
  public SubAccount createVirtualSubAccount(String subAccountString) {
    return executeSync(binanceApiService.createVirtualSubAccount(subAccountString, System.currentTimeMillis()));
  }
  
  @Override
  public void universalTransfer(String toEmail,String fromAccountType,String toAccountType,String asset,String amount) {
      executeSync(binanceApiService.universalTransfer(toEmail,fromAccountType,toAccountType, asset,amount,System.currentTimeMillis()));
  }
  
  @Override
  public Map<String, Object> enableSubAccountFutures(String email) {
    return executeSync(binanceApiService.enableSubAccountFutures(email, System.currentTimeMillis()));
  }
  
  @Override
  public DepositAddress getDepositAddress(String asset) {
    return executeSync(binanceApiService.getDepositAddress(asset, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
  }

  @Override
  public DustTransferResponse convertDustToBnb(List<String> assets) {
    return executeSync(binanceApiService.dustTransfer(assets, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, currentTimeMillis()));
  }

  // User stream endpoints

  @Override
  public String startUserDataStream() {
    return executeSync(binanceApiService.startUserDataStream()).toString();
  }

  @Override
  public void keepAliveUserDataStream(String listenKey) {
    executeSync(binanceApiService.keepAliveUserDataStream(listenKey));
  }

  @Override
  public void closeUserDataStream(String listenKey) {
    executeSync(binanceApiService.closeAliveUserDataStream(listenKey));
  }
  
  @Override
  public void transferAsset(String asset, String type, String amount) {
    executeSync(binanceApiService.transferAsset(asset, type, amount,BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, currentTimeMillis()));
  }
  
  @Override
  public Map<String, Object> transferAsset(String type, Long startTime, Long endTime) {
    return executeSync(binanceApiService.transferAsset(type, startTime, endTime, 100, currentTimeMillis()));
  }
  
  @Override
  public  ArrayList<Map<String, Object>> getUserAsset(String asset, Boolean needBtcValuation) {
    return executeSync(binanceApiService.getUserAsset(asset, needBtcValuation, BinanceApiConstants.DEFAULT_RECEIVING_WINDOW, currentTimeMillis()));
  }
  
  @Override
  public ApiRestriction apiRestrictions() {
    return executeSync(binanceApiService.apiRestrictions(currentTimeMillis()));
  }
  
  @Override
  public List<TransferHistory> getInternalTransferHistory() {
    return executeSync(binanceApiService.getInternalTransferHistory(currentTimeMillis()));
  }
  
  @Override
  public List<TransferHistory> getInternalTransferHistory(String toEmail,Integer page) {
    return executeSync(binanceApiService.getInternalTransferHistory(toEmail ,page,currentTimeMillis()));
  }
  
  @Override
  public List<Map<String, Object>> getEarnWithdrawals(String lendingType, Long startTime, Long endTime){
	  return executeSync(binanceApiService.getEarnWithdrawals(lendingType ,100L,startTime, endTime,currentTimeMillis()));
  }
  
  @Override
  public List<Map<String, Object>> getEarnDeposits(String lendingType, Long startTime, Long endTime){
	  return executeSync(binanceApiService.getEarnDeposits(lendingType ,100L,startTime, endTime,currentTimeMillis()));
  }
  
  @Override
  public Map<String, Object> transferToMaster(String asset,String amount){
	  return executeSync(binanceApiService.transferToMaster(asset,amount,currentTimeMillis()));
  }
  
  
}
