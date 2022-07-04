# CurrencyChecker
Compare Exchange Rates of different currencies and show GIF

Создать сервис, который обращается к сервису курсов валют, и отдает gif в ответ:  

если курс по отношению к рублю за сегодня стал выше вчерашнего,  
то отдаем рандомную отсюда 
```https://giphy.com/search/rich  ```\
если ниже - отсюда ```https://giphy.com/search/broke ```
\
\
Ссылки  
REST API курсов валют - https://docs.openexchangerates.org/  
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide  
\
Must Have:  
Сервис на Spring Boot 2 + Java / Kotlin  
Запросы приходят на HTTP endpoint, туда передается код валюты  
Для взаимодействия с внешними сервисами используется Feign  
Все параметры (валюта по отношению к которой смотрится курс,   
адреса внешних сервисов и т.д.) вынесены в настройки  
На сервис написаны тесты   
(для мока внешних сервисов можно использовать @mockbean или WireMock)   
Для сборки должен использоваться Gradle  
Результатом выполнения должен быть репо на GitHub с инструкцией по запуску  
\
Nice to Have  
Сборка и запуск Docker контейнера с этим сервисом


Инструкция по работе:
---
1.1 Запуск через .jar:
---
```
java -jar currencyChecker-0.0.1-SNAPSHOT.jar
```
1.2 Запуск через docker:
---
```
docker-compose up -d
```
2.Endpoints:
---  
root URL: localhost:9000


Получить гифку - сравнение котировок происходит относительно валюты по умолчанию (USD):

    GET /currency/compare-base-with/***
    вместо *** любая доступная валюта из списка:

"AED"
"AFN"
"ALL"
"AMD"
"ANG"
"AOA"
"ARS"
"AUD"
"AWG"
"AZN"
"BAM"
"BBD"
"BDT"
"BGN"
"BHD"
"BIF"
"BMD"
"BND"
"BOB"
"BRL"
"BSD"
"BTC"
"BTN"
"BWP"
"BYN"
"BZD"
"CAD"
"CDF"
"CHF"
"CLF"
"CLP"
"CNH"
"CNY"
"COP"
"CRC"
"CUC"
"CUP"
"CVE"
"CZK"
"DJF"
"DKK"
"DOP"
"DZD"
"EGP"
"ERN"
"ETB"
"EUR"
"FJD"
"FKP"
"GBP"
"GEL"
"GGP"
"GHS"
"GIP"
"GMD"
"GNF"
"GTQ"
"GYD"
"HKD"
"HNL"
"HRK"
"HTG"
"HUF"
"IDR"
"ILS"
"IMP"
"INR"
"IQD"
"IRR"
"ISK"
"JEP"
"JMD"
"JOD"
"JPY"
"KES"
"KGS"
"KHR"
"KMF"
"KPW"
"KRW"
"KWD"
"KYD"
"KZT"
"LAK"
"LBP"
"LKR"
"LRD"
"LSL"
"LYD"
"MAD"
"MDL"
"MGA"
"MKD"
"MMK"
"MNT"
"MOP"
"MRU"
"MUR"
"MVR"
"MWK"
"MXN"
"MYR"
"MZN"
"NAD"
"NGN"
"NIO"
"NOK"
"NPR"
"NZD"
"OMR"
"PAB"
"PEN"
"PGK"
"PHP"
"PKR"
"PLN"
"PYG"
"QAR"
"RON"
"RSD"
"RUB"
"RWF"
"SAR"
"SBD"
"SCR"
"SDG"
"SEK"
"SGD"
"SHP"
"SLL"
"SOS"
"SRD"
"SSP"
"STD"
"STN"
"SVC"
"SYP"
"SZL"
"THB"
"TJS"
"TMT"
"TND"
"TOP"
"TRY"
"TTD"
"TWD"
"TZS"
"UAH"
"UGX"
"USD"
"UYU"
"UZS"
"VES"
"VND"
"VUV"
"WST"
"XAF"
"XAG"
"XAU"
"XCD"
"XDR"
"XOF"
"XPD"
"XPF"
"XPT"
"YER"
"ZAR"
"ZMW"
"ZWL"

