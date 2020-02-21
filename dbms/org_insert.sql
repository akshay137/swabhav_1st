use swabhav;

insert into Organizer
	(orgName) values
    ("GSMarketing"),
    ("SomeOtherMarketing");

insert into Exhibitions
	(exbName, exbLocation, orgId)
    values
    ("IIMTF", "Ranchi", 1),
    ("IIMTF", "Patna", 1),
    ("IIMTF", "Ahemadabad", 1),
    ("E3", "Mumbai", 2),
    ("Comicon", "Mumbai", 2);

insert into Visitors
	(visName, exbId)
    values
    ("abc", 1),
    ("def", 2),
    ("abc", 3),
    ("hello", 4),
    ("bye", 5),
    ("goku", 5),
    ("saitam", 5);