SELECT 
cg.id, 
cg.name, 
Cast(cg.createdAt as varchar) as createdAt, 
Cast(cg.updatedAt as varchar) as updatedAt
from categories 
as cg INNER Join 
subcategories as scg 
on cg.id == scg.subCategoryId 
WHERE categoryId = ?
ORDER BY cg.id ASC 