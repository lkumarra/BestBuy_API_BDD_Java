Select DISTINCT 
products.id, 
products.name,
products.type,
products.price, 
products.upc,
products.shipping,
products.description,
products.manufacturer,
products.model,
products.url, 
products.image, 
Cast(products.createdAt as Varchar) as createdAt,
Cast(products.updatedAt as Varchar) as updatedAt
from products where products.name = ?