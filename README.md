Családfa

Az alkalmazás regisztrációt követően lehetővé teszi, hogy a felhasználók családfákat hozzanak létre. A családfa tárolja a felhasználó által rögzített személyek közötti rokoni viszonyokat. Továbbá feljegyzésre kerülnek a családot érintő fontos események is, mint például a születések, házasságkötések, válások és halálozások is.

Egyed-kapcsolat modell

A Személy entitásnak 2 rekurzív kapcsolata van, Anyja és Apja melyek 1:N típusúak, mivel egy személynek csak egy anyja/apja van, de egy személynek lehet több gyermeke is. Az Eseményt és a Személyt az Esemény része N:N-hez típusú kapcsolat köti össze, mivel egy személynek több eseménye is van, és egy eseményhez több személy is tartozhat (pl. esküvő). A Családfa és Személy egyed között szintén N:N típusú kapcsolat fedezhető fel, mivel egy személy több családhoz is tartozhat, és egy családfa nyilván több személyhez tartozik.

Relációs adatbázisséma

Felhasználó(id, név, jelszó)

Családfa(id, név)

Személy(id, keresztnév, vezetéknév, nem, születési dátum, anyja_id, apja_id)

Esemény(id, jelleg, mikor, név)

Esemény_része(esemény_id, szeméy_id, szerep)

Családfa_része(családfa_id, személy_id)

Normalizálás

1NF teljesül, mert minden relációsséma minden attribútuma atomi (nem többértékű és nem összetett).

2NF teljesül, mert 1 attribútumból áll a kulcs (Felhasználó, Családfa, Személy, Esemény), nincs a sémában másodlagos attribútum (Családfa_része) vagy relációsséma minden másodlagos attribútuma teljesen függ bármely kulcstól (Esemény_része).

3NF teljesül, mert nincs másodlagos attribútum a sémában (Családfa_része) vagy ha van, akkor azok közvetlenül függnek bármely kulcstól a sémában (nincs tranzitív függés).

Táblatervek

Összetett lekérdezések

Kiírja a napok különbségét egy személy legutolsó válása és a legutolsó házassága között. Amennyiben ez szám kisebb vagy egyenlő nullával, az aktuális személynek nincs aktív házassága, azaz hozzárendelhető egy új házassághoz.

A házasság és válás listázásához összekapcsoljuk az event és part_of_event táblákat, listázzuk azokat az eseményeket aminek a típusa MARRIGE/DIVORCE és a person_id oszlop megegyezik a kért személy azonosítójával, majd csak a legfrissebb ilyen eseményt tároljuk (DESC LIMIT 1).

ON  1 = 1 azért szükséges, hogy a kapcsolt táblák minden sorát biztosan lássak (habár esetünkben ezeknek a száma 1 vagy 0)

megvalósítás: adatb_csaladfa/csaladfa/src/main/java/com/csaladfa/DAO/EventRepository.java:137

# SELECT

DATEDIFF(d.date, m.date) AS date_difference

# FROM

(SELECT date FROM event JOIN part_of_event ON event.id = part_of_event.event_id

# WHERE

person_id = ? AND type = 'MARRIAGE' ORDER BY date

DESC LIMIT 1)m

# JOIN

(SELECT date FROM event JOIN part_of_event ON event.id = part_of_event.event_id

# WHERE

person_id = ? AND type = 'DIVORCE' ORDER BY date DESC

LIMIT 1) d

# ON 1 = 1;

Megszámolja az adott személynek hány házassága, és hány válása volt.

megvalósítás: adatb_csaladfa/csaladfa/src/main/java/com/csaladfa/DAO/EventRepository.java:115

megvalósítás: adatb_csaladfa/csaladfa/src/main/java/com/csaladfa/DAO/EventRepository.java:123

SELECT COUNT(type

FROM event JOIN part_of_event ON event.id = part_of_event.event_id GROUP BY person_id, type

HAVING person_id = ? AND type = 'MARRIAGE’

SELECT COUNT(type)

FROM event

JOIN part_of_event ON event.id = part_of_event.event_id

GROUP BY person_id, type

HAVING person_id = ? AND type = 'DIVORCE'

A lekérdezés megszámolja, hogy az adott személynek hány gyermeke van (hány személy father_id/mother_id -ja egyezik meg az adott személy id-jával). Az, hogy father_id vagy mother_id –ra keresünk a kódban van megvalósítva.

megvalósítás: adatb_csaladfa/csaladfa/src/main/java/com/csaladfa/DAO/EventRepository.java:176

SELECT number_of_children

FROM person

JOIN (SELECT father_id, COUNT(father_id) AS number_of_children

FROM person

GROUP BY father_id HAVING father_id = ?)

AS parent

ON person.id = parent.father_id;

Megvalósítás, funkciók

A megvalósítás Java nyelven (JDK 21.0.5 2024-10-15), Java Spring Boot (3.3.5) és ThymeLeaf(3.1.2) segítségével történt. Az adatbázishoz MySql (8.0.40-0ubuntu0.22.04.1)-t használtam, és JDBC(6.1.14) segítségével csatlakoztam a lokális adatbázishoz, a programban JPA-val modelleztem az adatbázist.

További info a csaladfa/pom.xml fájlban

Lehetőség van bejelentkezni és regisztrálni. Bejelentkezés után a felhasználó megtekintheti a már adatbázisban lévő személyek szüleit és gyermekeit. A felhasználó adhat az adatbázishoz személyeket, eseményeket és lehetősége van a személyek utólagos módosítására és eseményekhez rendelésére, valamint mindkettő törlésére. Azt a személyt rendelhetjük esküvőhöz, aki az adatbázis szerint jelenleg nincs házasságban (frissebb DIVORCE eseménye van mint MARRIAGE).
A felhasználó létrehozhat családfákat amikhez hozzárendelheti az adatbázisban lévő személyeket, illetve törölheti a családfát, vagy az adott személyt a családfából.

Beüzemelés

Adatbázis:

az adatbázis és user létrehozásához készítettem egy setup.sql-filet

szükség van egy sql szerverre a localhost:3306-os portján

(csaladfa/src/main/resources/application.properties:3)

a szerveren kelleni fog egy family_tree_db nevű database

(csaladfa/src/main/resources/application.properties:3)

kell még egy user nevű és user jelszavú felhasználó, akinek hozzáférése van az előbbi adatbázishoz, illetve rendelkezik a megfelelő módosítási jogokkal

(csaladfa/src/main/resources/application.properties:4-5)

