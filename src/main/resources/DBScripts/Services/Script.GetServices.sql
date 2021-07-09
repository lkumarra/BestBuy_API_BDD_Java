SELECT 
[services].[id], 
[services].[name],
CAST([services].[createdAt] as varchar) as createdAt,
CAST([services].[updatedAt] as varchar) as updatedAt
from services ORDER BY [services].[id] ASC LIMIT 10