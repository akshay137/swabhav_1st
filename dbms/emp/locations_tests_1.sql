use swabhav;

select * from REGIONS;
select * from COUNTRIES;
select * from LOCATIONS;
select distinct REGION_ID from COUNTRIES;

-- display regions with no country
select REGION_NAME, count(COUNTRIES.COUNTRY_ID) as cc from REGIONS
	left join COUNTRIES
    on COUNTRIES.REGION_ID=REGIONS.REGION_ID
    group by REGIONS.REGION_NAME having cc=0;

-- display countries with no state
select COUNTRY_NAME, count(LOCATIONS.LOCATION_ID) as sc from COUNTRIES
	left join LOCATIONS
    on COUNTRIES.COUNTRY_ID=LOCATIONS.COUNTRY_ID
    group by COUNTRIES.COUNTRY_NAME having sc=0;

-- display region name, country name, state name
select r.REGION_NAME, c.COUNTRY_NAME, s.STATE_PROVINENCE, s.CITY
from LOCATIONS as s
inner join COUNTRIES as c
on s.COUNTRY_ID=c.COUNTRY_ID
inner join REGIONS as r
on r.REGION_ID=c.REGION_ID;

-- insert swabhav techlabs in locations/state map to india and asia
delete from LOCATIONS where LOCATION_ID=2000;
insert into LOCATIONS (
	LOCATION_ID, STREET_ADDRESS, POSTAL_CODE, CITY, STATE_PROVINENCE,
		COUNTRY_ID)
	values (
		2000, 'M.V. Road', '400069', 'Mumbai', 'Maharashtra', 'IN'
    );

-- filter data based on mumbai
select r.REGION_NAME, c.COUNTRY_NAME, s.STATE_PROVINENCE, s.CITY
from LOCATIONS as s
inner join COUNTRIES as c
on s.COUNTRY_ID=c.COUNTRY_ID
inner join REGIONS as r
on r.REGION_ID=c.REGION_ID
where s.CITY='MUMBAI';