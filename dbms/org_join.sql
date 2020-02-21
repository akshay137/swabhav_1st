use swabhav;

select
Visitors.visName, Exhibitions.exbName, Organizer.orgName
from ((Organizer inner join Exhibitions
		ON Organizer.orgId=Exhibitions.orgId)
        inner join Visitors
		ON Visitors.exbId=Exhibitions.exbId);