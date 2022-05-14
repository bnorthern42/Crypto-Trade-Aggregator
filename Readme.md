# Crypto Trade Aggregator

pulls in CSV dumps of your trades and aggregates them into PostgreSQL

## This is not production software - use at own risk


### Reason for making this:
- much of the current tax software costs hundreds of dollars for high frequency trading
- if you have 10k to 1 Billion trades a year, it should not cost a kidney to do your taxes
- will make report that then you can upload to XYZ tax software where it looks like you only made a few trades with aggegation


### Exchange format Supported
- KuCoin

### To Configure
- Go to: [configuration file here](src/main/resources/application-dev.yml.example)
- copy to 'application-dev.yml'
- change necessary information (Port, DB Name, and credentials)

#### To Upload

Make a POST request with a multipart-form with name 'file':
```
POST: localhost:3030/api/v1/uploadKucoin
```
Returns status message

### To Aggregate

Make a GET request:
```
GET: localhost:3030/api/v1/aggKucoinSymbol
```
Returns JSON of aggregated trades

### Note this is a WIP

### Contributing
- See: [CONTRIBUTING.md](CONTRIBUTING.md)

### TODO's
- add aggregation SQL to TradesRepository
- print aggregated report to CSV
