SELECT image, name, c2.title as role, p2.title as programmeTitle
FROM people
         INNER JOIN credits c on people.id = c.person_id
         INNER JOIN creditrole c2 on c.role_id = c2.id
         LEFT JOIN programme p2 on c.programme_id = p2.id
WHERE people.id IN (
    SELECT id
    FROM credits
    WHERE c.programme_id = (
        SELECT id
        FROM programme as p
        WHERE id IN (
            SELECT programme_id
            FROM programme_epg_entries
            WHERE epgprogrammes_id IN (
                SELECT id
                FROM programme_epg
                WHERE epgidentifier = ?
            ))
    )
);