--name: get-locations
-- Gets all of the available locations
SELECT *
FROM location

--name: find-location-by-name
-- Get a particular location, by name
SELECT *
FROM location
WHERE name = :name

--name: insert-location!
-- Adds a location to the list of locations
INSERT INTO location (name)
VALUES (:name);

--name: update-location!
-- Updates a location from the list of locations
UPDATE location
SET name = :name
WHERE id = :id

--name: delete-location!
-- Remove a location from the list of locations
DELETE FROM location
WHERE id = :id
