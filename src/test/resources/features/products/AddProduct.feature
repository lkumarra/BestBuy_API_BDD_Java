@Products @PostProducts
Feature: PostProducts
  In Order to post products
  I want to told to verify products
  API :- POST /products

  @GetProducts @PositiveScenarios
  Scenario: Post a products
    Given I am a valid user
    When I create a product with name as 'Added Product', type as 'Added Type', price as 10000, shipping as 200, upc as 'AddedUPC',description as 'Added Description',manufacturer as 'Added Manufacturer', model as 'Added Model',url as 'Added Url' image as 'Added Image'
    Then Response should be returned with status code 'CREATED'
    And Verify product created in Db.

  @GetProducts @NegativeScenarios
  Scenario Outline: Post a products with inavalid data
    Given I am a valid user
    When I try create a product with name as '<Name>', type as '<type>', price as <price>, shipping as <shipping>, upc as '<upc>',description as '<description>',manufacturer as '<manufacturer>', model as '<model>', url as '<url>' image as '<image>'
    Then Response should not be returned with name as '<errorName>',message as '<message>', status code 'BADREQUEST' and errors as "<errors>"

    Examples: 
      | Scenario                                         | Name        | type        | price | shipping | upc       | description      | manufacturer      | model      | url      | image      | errorName  | message            | errors                                                 |
      | Try to create product with name as null          | NULL        | AddedType1  |     1 |        1 | AddedUpc1 | AddedDescription | AddedManufacturer | AddedModel | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'name' should be string                                |
      | Try to create product with name as empty         | EMPTY       | AddedType2  |     2 |        2 | AddedUpc1 | AddedDescription | AddedManufacturer | AddedModel | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'name' should NOT be shorter than 1 characters         |
      | Try to create product with type as null          | AddedName3  | NULL        |     4 |        4 | AddedUpc1 | AddedDescription | AddedManufacturer | AddedModel | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'type' should be string                                |
      | Try to create product with type as empty         | AddedName4  | EMPTY       |     5 |        5 | AddedUpc1 | AddedDescription | AddedManufacturer | AddedModel | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'type' should NOT be shorter than 1 characters         |
      | Try to create product with upc as null           | AddedName12 | AddedType12 |    13 |       13 | NULL      | AddedDescription | AddedManufacturer | AddedModel | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'upc' should be string                                 |
      | Try to create product with upc as empty          | AddedName13 | AddedType13 |    14 |       14 | EMPTY     | AddedDescription | AddedManufacturer | AddedModel | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'upc' should NOT be shorter than 1 characters          |
      | Try to create product with description as null   | AddedName15 | AddedType15 |    16 |       16 | AddedUpc1 | NULL             | AddedManufacturer | AddedModel | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'description' should be string                         |
      | Try to create product with description as empty  | AddedName16 | AddedType16 |    17 |       17 | AddedUpc1 | EMPTY            | AddedManufacturer | AddedModel | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'description' should NOT be shorter than 1 characters  |
      | Try to create product with manufacturer as null  | AddedName18 | AddedType18 |    19 |       19 | AddedUpc1 | AddedDescription | NULL              | AddedModel | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'manufacturer' should be string                        |
      | Try to create product with manufacturer as empty | AddedName19 | AddedType19 |    20 |       20 | AddedUpc1 | AddedDescription | EMPTY             | AddedModel | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'manufacturer' should NOT be shorter than 1 characters |
      | Try to create product with model as null         | AddedName21 | AddedType21 |    22 |       22 | AddedUpc1 | AddedDescription | AddedManufacturer | NULL       | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'model' should be string                               |
      | Try to create product with model as empty        | AddedName22 | AddedType22 |    23 |       23 | AddedUpc1 | AddedDescription | AddedManufacturer | EMPTY      | AddedUrl | AddedImage | BadRequest | Invalid Parameters | 'model' should NOT be shorter than 1 characters        |
      | Try to create product with url as null           | AddedName24 | AddedType24 |    25 |       25 | AddedUpc1 | AddedDescription | AddedManufacturer | AddedModel | NULL     | AddedImage | BadRequest | Invalid Parameters | 'url' should be string                                 |
      | Try to create product with url as empty          | AddedName25 | AddedType25 |    26 |       26 | AddedUpc1 | AddedDescription | AddedManufacturer | AddedModel | EMPTY    | AddedImage | BadRequest | Invalid Parameters | 'url' should NOT be shorter than 1 characters          |
      | Try to create product with image as null         | AddedName27 | AddedType27 |    28 |       28 | AddedUpc1 | AddedDescription | AddedManufacturer | AddedModel | AddedUrl | NULL       | BadRequest | Invalid Parameters | 'image' should be string                               |
      | Try to create product with image as empty        | AddedName28 | AddedType28 |    29 |       29 | AddedUpc1 | AddedDescription | AddedManufacturer | AddedModel | AddedUrl | EMPTY      | BadRequest | Invalid Parameters | 'image' should NOT be shorter than 1 characters        |
