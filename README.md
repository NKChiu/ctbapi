# Project : ctbapi
- 功能:
  1. 測試呼叫查詢幣別對應表資料 API (getAllCurrency)
  2. 測試呼叫新增幣別對應表資料 API (addCurrency)
  3. 測試呼叫更新幣別對應表資料 API (updateCurrency)
  4. 測試呼叫刪除幣別對應表資料 API (deleteCurrency)
  5. 測試呼叫 coindesk API (getCoinDeskApi)
  6. 測試呼叫資料轉換  API (getTransCoinDeskApi)

- 相關 : 
  - Java : 8
  - Spring-boot : 2.2.2.RELEASE
  - Maven 
  - DB : h2
    - username: sa
    - password: 123456

- URL :
  - 查詢幣別對應表資料 API (get): http://localhost:8091/getAllCurrency
  - 新增幣別對應表資料 API (post): http://localhost:8091/addCurrency
    <details>
    <summary>Input json sample</summary>
      
      ```
      {
          "code": "TWD",
          "codeChn": "台幣",
          "updateUser":"SYSTEM"
      }      
      ```
    </details>
  - 更新幣別對應表資料 API (post): http://localhost:8091/updateCurrency
    <details>
    <summary>Input json sample</summary>
      
      ```
     {
          "code": "TWD",
          "codeChn": "台W幣",
          "updateUser":"SYSTEM2"
      } 
      ```
    </details>

  - 刪除幣別對應表資料 API (post): http://localhost:8091/deleteCurrency
    <details>
    <summary>Input json sample</summary>
      
      ```
      {
          "code": "TWD"
      }
      ```
    </details>

  - coindesk API (get): http://localhost:8091/getCoinDeskApi
  - 資料轉換  API (get): http://localhost:8091/getTransCoinDeskApi
  - H2 db console : http://localhost:8091/console



