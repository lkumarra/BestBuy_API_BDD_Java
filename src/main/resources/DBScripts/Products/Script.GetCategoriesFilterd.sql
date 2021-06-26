SELECT products.id as productid, 
categories.id, 
categories.name, 
CAST(categories.updatedAt as VARCHAR) as updatedAt, 
CAST(categories.createdAt as VARCHAR) as createdAt 
from categories INNER JOIN productcategory 
on categories.id = productcategory.categoryId INNER JOIN 
products on products.id = productcategory.productId WHERE products.id = ? ORDER by
products.id ASC