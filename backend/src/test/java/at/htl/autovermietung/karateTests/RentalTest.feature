Feature: Rental Test
  Background:
    * url baseUrl


  Scenario: list rentals - working
    Given path 'rental'
    When method GET
    Then status 200
    And print 'Rentals listed'

  Scenario: add rental
    Given path 'rental/add-rental'
    And header Content-Type = 'application/json'
    And request {"car": {"cost": 120.0,  "licensePlate": "SR-373MM",  "model": "E-400",  "name": "Mercedes",  "ps": 230.0  },  "endDate": "2020-05-24",  "id": 6,  "renter": {  "id": 12,  "name": "Raid"  },  "startDate": "2021-01-06"  }
    When method POST
    Then status 200
    And print 'new Rental added'
