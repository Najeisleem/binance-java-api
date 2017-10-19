package com.binance.api.client;

import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;
import com.binance.api.client.domain.market.AggTrade;
import com.binance.api.client.domain.market.BookTicker;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;

import java.util.List;

/**
 * Binance API façade, supporting synchronous/blocking access Binance's REST API.
 */
public interface BinanceApiRestClient {

  // General endpoints

  /**
   * Test connectivity to the Rest API.
   */
  void ping();

  /**
   * Check server time.
   */
  Long getServerTime();

  // Market Data endpoints

  /**
   * Get order book of a symbol.
   *
   * @param symbol ticker symbol (e.g. ETHBTC)
   * @param limit depth of the order book (max 100)
   */
  OrderBook getOrderBook(String symbol, Integer limit);

  /**
   * Get compressed, aggregate trades. Trades that fill at the time, from the same order, with
   * the same price will have the quantity aggregated.
   *
   * If both <code>startTime</code> and <code>endTime</code> are sent, <code>limit</code>should not
   * be sent AND the distance between <code>startTime</code> and <code>endTime</code> must be less than 24 hours.
   *
   * @param symbol symbol to aggregate (mandatory)
   * @param fromId ID to get aggregate trades from INCLUSIVE (optional)
   * @param limit Default 500; max 500 (optional)
   * @param startTime Timestamp in ms to get aggregate trades from INCLUSIVE (optional).
   * @param endTime Timestamp in ms to get aggregate trades until INCLUSIVE (optional).
   * @return a list of aggregate trades for the given symbol
   */
  List<AggTrade> getAggTrades(String symbol, String fromId, Integer limit, Long startTime, Long endTime);

  /**
   * Return the most recent aggregate trades for <code>symbol</code>
   *
   * @see #getAggTrades(String, String, Integer, Long, Long)
   */
  List<AggTrade> getAggTrades(String symbol);

  /**
   * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
   *
   * @param symbol symbol to aggregate (mandatory)
   * @param interval candlestick interval (mandatory)
   * @param limit Default 500; max 500 (optional)
   * @param startTime Timestamp in ms to get candlestick bars from INCLUSIVE (optional).
   * @param endTime Timestamp in ms to get candlestick bars until INCLUSIVE (optional).
   * @return a candlestick bar for the given symbol and interval
   */
  List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime);

  /**
   * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.
   *
   * @see #getCandlestickBars(String, CandlestickInterval, Integer, Long, Long)
   */
  List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval);

  /**
   * Get 24 hour price change statistics.
   *
   * @param symbol ticker symbol (e.g. ETHBTC)
   */
  TickerStatistics get24HrPriceStatistics(String symbol);

  /**
   * Get Latest price for all symbols.
   */
  List<TickerPrice> getAllPrices();

  /**
   * Get best price/qty on the order book for all symbols.
   */
  List<BookTicker> getBookTickers();

  // Account endpoints

  /**
   * Send in a new order.
   *
   * @param order the new order to submit.
   * @return a response containing details about the newly placed order.
   */
  NewOrderResponse newOrder(NewOrder order);

  /**
   * Test new order creation and signature/recvWindow long. Creates and validates a new order but does not send it into the matching engine.
   *
   * @param order the new TEST order to submit.
   */
  void newOrderTest(NewOrder order);

  /**
   * Check an order's status.
   * @param orderStatusRequest order status request options/filters
   *
   * @return an order
   */
  Order getOrderStatus(OrderStatusRequest orderStatusRequest);

  /**
   * Cancel an active order.
   *
   * @param cancelOrderRequest order status request parameters
   */
  void cancelOrder(CancelOrderRequest cancelOrderRequest);

  /**
   * Get all open orders on a symbol.
   *
   * @param orderRequest order request parameters
   * @return a list of all account open orders on a symbol.
   */
  List<Order> getOpenOrders(OrderRequest orderRequest);

  /**
   * Get all account orders; active, canceled, or filled.
   *
   * @param orderRequest order request parameters
   * @return a list of all account orders
   */
  List<Order> getAllOrders(AllOrdersRequest orderRequest);

  /**
   * Get current account information.
   */
  Account getAccount(Long recvWindow, Long timestamp);

  /**
   * Get current account information using default parameters.
   */
  Account getAccount();

  // User stream endpoints

  /**
   * Start a new user data stream.
   *
   * @return a listen key that can be used with data streams
   */
  String startUserDataStream();

  /**
   * PING a user data stream to prevent a time out.
   *
   * @param listenKey listen key that identifies a data stream
   */
  void keepAliveUserDataStream(String listenKey);

  /**
   * Close out a new user data stream.
   *
   * @param listenKey listen key that identifies a data stream
   */
  void closeUserDataStream(String listenKey);
}