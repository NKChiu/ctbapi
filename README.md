
- [Project : ctbapi](#project--ctbapi)
  - [功能](#功能)
  - [相關](#相關)
  - [URL](#url)
  - [Table SQL](#table-sql)


# Project : ctbapi

## 功能
  1. <b>測試呼叫查詢幣別對應表資料 API</b> (getAllCurrency)
  2. <b>測試呼叫新增幣別對應表資料 API</b> (addCurrency)
  3. <b>測試呼叫更新幣別對應表資料 API</b> (updateCurrency)
  4. <b>測試呼叫刪除幣別對應表資料 API</b> (deleteCurrency)
  5. <b>測試呼叫 coindesk API</b> (getCoinDeskApi)
  6. <b>測試呼叫資料轉換  API</b> (getTransCoinDeskApi)

## 相關 
  - Java : 8
  - Spring-boot : 2.2.2.RELEASE
  - Maven 
  - DB : h2
    - username: sa
    - password: 123456

## URL
  - <b>查詢幣別對應表資料 API</b> (get): http://localhost:8091/getAllCurrency
    <details>
    <summary>Result Output json</summary>
      
      ```json
      {
          "success": true,
          "returnMessage": null,
          "currencyList": [
              {
                  "code": "USD",
                  "codeChn": "美元"
              },
              {
                  "code": "GBP",
                  "codeChn": "英鎊"
              },
              {
                  "code": "EUR",
                  "codeChn": "歐元"
              }
          ]
      }
      ```
    </details>
    
  - <b>新增幣別對應表資料 API</b> (post): http://localhost:8091/addCurrency
    <details>
    <summary>Input json sample</summary>
      
      ```json
      {
          "code": "TWD",
          "codeChn": "台幣",
          "updateUser":"SYSTEM"
      }      
      ```
    </details>
    <details>
    <summary>Result Output json</summary>
        
      ```json
      {
          "success": true,
          "returnMessage": "新增成功",
          "code": null,
          "codeChn": null,
          "updateUser": null
      }
      ```
    </details>
  - <b>更新幣別對應表資料 API</b> (post): http://localhost:8091/updateCurrency
    <details>
    <summary>Input json sample</summary>
      
      ```json
     {
          "code": "TWD",
          "codeChn": "台W幣",
          "updateUser":"SYSTEM2"
      } 
      ```
    </details>
    <details>
    <summary>Result Output json</summary>
        
      ```json
      {
          "success": true,
          "returnMessage": "更新成功",
          "code": "TWD",
          "codeChn": "台W幣",
          "updateUser": "SYSTEM2"
      }
      ```
    </details>

  - <b>刪除幣別對應表資料 API</b> (post): http://localhost:8091/deleteCurrency
    <details>
    <summary>Input json sample</summary>
      
      ```json
      {
          "code": "TWD"
      }
      ```
    </details>
    <details>
    <summary>Result Output json</summary>
        
      ```json
      {
          "success": true,
          "returnMessage": "刪除成功",
          "code": null,
          "codeChn": null,
          "updateUser": null
      }
      ```
    </details>
  - <b>coindesk API</b> (get): http://localhost:8091/getCoinDeskApi
    <details>
    <summary>Result Output json</summary>
        
    ```json
    {
        "success": true,
        "returnMessage": null,
        "time": {
            "updated": "Jul 29, 2021 11:43:00 UTC",
            "updatedISO": "2021-07-29T11:43:00+00:00",
            "updateduk": "Jul 29, 2021 at 12:43 BST"
        },
        "bpi": {
            "EUR": {
                "code": "EUR",
                "symbol": "&euro;",
                "rate": "33,452.1139",
                "description": "Euro",
                "rate_float": 33452.1139
            },
            "GBP": {
                "code": "GBP",
                "symbol": "&pound;",
                "rate": "28,458.7120",
                "description": "British Pound Sterling",
                "rate_float": 28458.712
            },
            "USD": {
                "code": "USD",
                "symbol": "&#36;",
                "rate": "39,725.3883",
                "description": "United States Dollar",
                "rate_float": 39725.3883
            }
        },
        "disclaimer": "This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
        "chartName": "Bitcoin"
    }
    ```
    </details>
  - <b>資料轉換  API</b> (get): http://localhost:8091/getTransCoinDeskApi
    <details>
    <summary>Result Output json</summary>
        
    ```json
    {
        "success": true,
        "returnMessage": null,
        "updateTime": "2021/07/29 19:45:00",
        "currencyInfo": {
            "EUR": {
                "code": "EUR",
                "codeChn": "歐元",
                "symbol": "€",
                "rate": "33,435.5768"
            },
            "GBP": {
                "code": "GBP",
                "codeChn": "英鎊",
                "symbol": "£",
                "rate": "28,444.6434"
            },
            "USD": {
                "code": "USD",
                "codeChn": "美元",
                "symbol": "$",
                "rate": "39,705.7500"
            }
        }
    }
    ```
    </details>

  - <b>H2 db console</b> : http://localhost:8091/console

## Table SQL
  <details>
  <summary>Result Output json</summary>
      
  ```sql
  DROP SEQUENCE SEQ_CURRENCY;
  DROP TABLE CURRENCY_ENTITY;

  CREATE TABLE CURRENCY_ENTITY( 		
    ID            NUMBER(22)          NOT NULL,	
    CODE          NVARCHAR2(22)       NOT NULL,	
    CODE_CHN      NVARCHAR2(22)       NOT NULL,	
    UPDATE_USER   NVARCHAR2(10)       NOT NULL,	
    UPDATE_DATE   TIMESTAMP  DEFAULT  SYSTIMESTAMP  NOT NULL
      );
      
  ALTER TABLE CURRENCY_ENTITY ADD CONSTRAINT PK_CURRENCY_ID PRIMARY KEY (ID);

  CREATE SEQUENCE SEQ_CURRENCY NOCACHE MAXVALUE 9223372036854770000 START WITH 1;

  COMMIT;


  ------- pre insert data ------------------------------------------------

  INSERT INTO CURRENCY_ENTITY(ID, CODE, CODE_CHN, UPDATE_USER, UPDATE_DATE)
  VALUES(SEQ_CURRENCY.NEXTVAL, 'USD', '美元', 'SYSTEM', SYSDATE);

  INSERT INTO CURRENCY_ENTITY(ID, CODE, CODE_CHN, UPDATE_USER, UPDATE_DATE)
  VALUES(SEQ_CURRENCY.NEXTVAL, 'GPB', '英鎊', 'SYSTEM', SYSDATE);

  INSERT INTO CURRENCY_ENTITY(ID, CODE, CODE_CHN, UPDATE_USER, UPDATE_DATE)
  VALUES(SEQ_CURRENCY.NEXTVAL, 'EUR', '歐元', 'SYSTEM', SYSDATE);

  COMMIT;
  ```
  </details>