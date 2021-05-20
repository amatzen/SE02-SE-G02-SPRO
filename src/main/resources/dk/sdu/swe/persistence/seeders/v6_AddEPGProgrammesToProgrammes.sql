INSERT INTO programme_epg_entries
SELECT p.id, e.id
FROM programme as p
         LEFT JOIN programme_epg as e ON p.title = e.title;