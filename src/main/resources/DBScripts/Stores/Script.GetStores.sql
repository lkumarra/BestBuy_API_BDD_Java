select DISTINCT storeservices.storeId, storeservices.serviceId, stores.name as storesName, stores.type, 
stores.address,stores.address2,
stores.city,stores.state, stores.zip, 
stores.lat, stores.lng, 
stores.hours,
stores.createdAt as storesCreatedAt, stores.updatedAt as storesUpdatedAt ,services.name as servicesName, services.createdAt as serviceCreatedAt,services.updatedAt as serviceUpdatedAt,
storeservices.createdAt as storeServiceCreatedAt,
storeservices.updatedAt as storeServiceUpdatedAt from storeservices INNER JOIN (select * from stores LIMIT 10) stores
on stores.id=storeservices.storeId INNER JOIN services on services.id = storeservices.serviceId ORDER by storeservices.storeId;