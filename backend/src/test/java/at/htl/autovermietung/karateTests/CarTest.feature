Feature: Car Test
  Background:
    * url baseUrl

  Scenario: list cars - working
    Given path 'car'
    When method GET
    Then status 200
    And print 'Cars listed'

  Scenario: add car - working
    Given path 'car/add-car'
    And header Content-Type = 'application/json'
    And request {cost: 60.0,licensePlate: 'LL-152QQ',model: 'Sportage',name: 'Kia',ps: 140.0}
    When method POST
    Then status 200
    And print 'new Car added'

  Scenario: update car
    Given path 'car/update-car'
    And header Content-Type = 'application/json'
    And request {cost: 60.0,licensePlate: 'LL-152QQ',model: 'CX-5',name: 'Mazda',ps: 140.0}
    When method PUT
    Then status 200
    And print 'Car updated'

  Scenario: list cars - working
    Given path 'car'
    When method GET
    Then status 200
    And print 'Cars listed'