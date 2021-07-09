SELECT cg.id, 
cg.name,
Cast(cg.createdAt as varchar) as createdAt, 
Cast(cg.updatedAt as varchar) as updatedAt
from categories 
as cg INNER Join categorypath as cp on cg.id == cp.categoryPathId 
where cp.categoryId = ? ORDER BY cg.id ASC