# Családfa

Az alkalmazás regisztrációt követően lehetővé teszi, hogy a felhasználók családfákat hozzanak létre. A családfa tárolja a felhasználó által rögzített személyek közötti rokoni viszonyokat. Továbbá feljegyzésre kerülnek a családot érintő fontos események is, mint például a születések, házasságkötések, válások és halálozások is.

## Egyed-kapcsolat modell

![](csaladfa_EK.jpg)

A Személy entitásnak 2 rekurzív kapcsolata van, Anyja és Apja melyek 1:N típusúak, mivel egy személynek csak egy anyja/apja van, de egy személynek lehet több gyermeke is. Az Eseményt és a Személyt az Esemény része N:N-hez típusú kapcsolat köti össze, mivel egy személynek több eseménye is van, és egy eseményhez több személy is tartozhat (pl. esküvő). A Családfa és Személy egyed között szintén N:N típusú kapcsolat fedezhető fel, mivel egy személy több családhoz is tartozhat, és egy családfa nyilván több személyhez tartozik.

## Relációs adatbázisséma

Felhasználó(**id**, név, jelszó)

Családfa(**id**, név)

Személy(**id**, keresztnév, vezetéknév, nem, születési dátum, anyja\_id, apja\_id)

Esemény(**id**, jelleg, mikor, név)

Esemény\_része(**esemény\_id**, **szeméy\_id, szerep**)

Családfa\_része(**családfa\_id**, **személy\_id**)

## Normalizálás

1NF teljesül, mert minden relációsséma minden attribútuma atomi (nem többértékű és nem összetett).

2NF teljesül, mert 1 attribútumból áll a kulcs (Felhasználó, Családfa, Személy, Esemény), nincs a sémában másodlagos attribútum (Családfa\_része) vagy relációsséma minden másodlagos attribútuma teljesen függ bármely kulcstól (Esemény\_része). 

3NF teljesül, mert nincs másodlagos attribútum a sémában (Családfa\_része) vagy ha van, akkor azok közvetlenül függnek bármely kulcstól a sémában (nincs tranzitív függés).

## Táblatervek

|**user**|||
| :- | :- | :- |
|id|int|Azonosító, *kulcs*|
|username|varchar(255)|Felhasználónév|
|password|varchar(255)|Jelszó|

|**family\_tree**|||
| :- | :- | :- |
|id|int|Azonosító, *kulcs*|
|name|varchar(255)|Családfa neve|

|**person**|||
| :- | :- | :- |
|id|int|Személy azonosító, *kulcs*|
|mother\_id|int|Külső kulcs a **person** tábla kulcsára|
|father\_id|int|Külső kulcs a **person** tábla kulcsára|
|first\_name|varchar(255)|Keresztnév|
|last\_name|varchar(255)|Vezetéknév|
|gender|varchar(1)|Nem|
|date\_of\_birth|date|Szül. dátum|

|**event**|||
| :- | :- | :- |
|id|int|Esemény azonosító, *kulcs*|
|type|varchar(255)|<p>Esemény típusa,</p><p>enum {BIRTH, DEATH, MARRIAGE, DIVORCE} –ként megvalósítva</p>|
|date|date|Esemény dátuma|
|name|varchar(255)|Esemény megnevezése|



|**part\_of\_event**|||
| :- | :- | :- |
|event\_id|int|Külső kulcs az **event** tábla kulcsára, *kulcs*|
|person\_id|int|Külső kulcs a **person** tábla kulcsára, *kulcs*|
|role|varchar(255)|Személy szerepe az eseményen (opcionális)|

|**part\_of\_family**|||
| :- | :- | :- |
|family\_id|int|Külső kulcs a **family\_tree** tábla kulcsára, *kulcs*|
|person\_id|int|Külső kulcs a **person** tábla kulcsára, *kulcs*|

## Összetett lekérdezések
1. Kiírja a napok különbségét egy személy legutolsó válása és a legutolsó házassága között. Amennyiben ez szám kisebb vagy egyenlő nullával, az aktuális személynek nincs aktív házassága, azaz hozzárendelhető egy új házassághoz.

   A házasság és válás listázásához összekapcsoljuk az *event* és *part\_of\_event* táblákat, listázzuk azokat az eseményeket aminek a típusa MARRIGE/DIVORCE és a *person\_id* oszlop megegyezik a kért személy azonosítójával, majd csak a legfrissebb ilyen eseményt tároljuk *(DESC LIMIT 1)*.

   ON  1 = 1 azért szükséges, hogy a kapcsolt táblák minden sorát biztosan lássak (habár esetünkben ezeknek a száma 1 vagy 0)

   
```sql
SELECT
DATEDIFF(d.date, m.date) AS date_difference
FROM
(SELECT date FROM event JOIN part_of_event ON event.id =
part_of_event.event_id
WHERE
person_id = ? AND type = 'MARRIAGE' ORDER BY date
DESC LIMIT 1)m
JOIN
(SELECT date FROM event JOIN part_of_event ON event.id =
part_of_event.event_id
WHERE
person_id = ? AND type = 'DIVORCE' ORDER BY date DESC
LIMIT 1) d
ON 1 = 1;
```

>  [megvalósítás: adatb\_csaladfa/csaladfa/src/main/java/com/csaladfa/DAO/EventRepository.java](https://github.com/bodorcy/adatb_csaladfa/blob/d39991cab3ff1061a294f8ae8810e2d9a46a3644/csaladfa/src/main/java/com/csaladfa/DAO/EventRepository.java#L137)


2. Megszámolja az adott személynek hány házassága, és hány válása volt.

  
```sql
SELECT COUNT(type)
FROM event JOIN part_of_event ON event.id = part_of_event.event_id
GROUP BY person_id, type
HAVING person_id = ? AND type = 'MARRIAGE’
SELECT COUNT(type)
```
```sql
FROM event
JOIN part_of_event ON event.id = part_of_event.event_id
GROUP BY person_id, type
HAVING person_id = ? AND type = 'DIVORCE'
```


> [megvalósítás: adatb\_csaladfa/csaladfa/src/main/java/com/csaladfa/DAO/EventRepository.java](https://github.com/bodorcy/adatb_csaladfa/blob/d39991cab3ff1061a294f8ae8810e2d9a46a3644/csaladfa/src/main/java/com/csaladfa/DAO/EventRepository.java#L115)

> [megvalósítás: adatb\_csaladfa/csaladfa/src/main/java/com/csaladfa/DAO/EventRepository.java](https://github.com/bodorcy/adatb_csaladfa/blob/d39991cab3ff1061a294f8ae8810e2d9a46a3644/csaladfa/src/main/java/com/csaladfa/DAO/EventRepository.java#L123)


3. A lekérdezés megszámolja, hogy az adott személynek hány gyermeke van (hány személy father\_id/mother\_id -ja egyezik meg az adott személy id-jával). Az, hogy father\_id vagy mother\_id –ra keresünk a kódban van megvalósítva.


```sql
SELECT number_of_children
FROM person
JOIN (SELECT father_id, COUNT(father_id) AS number_of_children
FROM person
GROUP BY father_id HAVING father_id = ?)
AS parent
ON person.id = parent.father_id;
```

> [megvalósítás: adatb\_csaladfa/csaladfa/src/main/java/com/csaladfa/DAO/PersonRepository.java](https://github.com/bodorcy/adatb_csaladfa/blob/d39991cab3ff1061a294f8ae8810e2d9a46a3644/csaladfa/src/main/java/com/csaladfa/DAO/PersonRepository.java#L176)


## Megvalósítás, funkciók

A megvalósítás Java nyelven (JDK 21.0.5 2024-10-15), Java Spring Boot (3.3.5) és ThymeLeaf(3.1.2) segítségével történt. Az adatbázishoz MySql (8.0.40-0ubuntu0.22.04.1)-t használtam, és JDBC(6.1.14) segítségével csatlakoztam a lokális adatbázishoz, a programban JPA-val modelleztem az adatbázist.

*További info a [csaladfa/pom.xml](https://github.com/bodorcy/adatb_csaladfa/blob/main/csaladfa/pom.xml) fájlban*

Lehetőség van bejelentkezni és regisztrálni. Bejelentkezés után a felhasználó megtekintheti a már adatbázisban lévő személyek szüleit és gyermekeit. A felhasználó adhat az adatbázishoz személyeket, eseményeket és lehetősége van a személyek utólagos módosítására és eseményekhez rendelésére, valamint mindkettő törlésére. Azt a személyt rendelhetjük esküvőhöz, aki az adatbázis szerint jelenleg nincs házasságban (frissebb DIVORCE eseménye van mint MARRIAGE).
A felhasználó létrehozhat családfákat amikhez hozzárendelheti az adatbázisban lévő személyeket, illetve törölheti a családfát, vagy az adott személyt a családfából. 



## Beüzemelés

### Adatbázis:

> az adatbázis és user létrehozásához készítettem egy [setup.sql](https://github.com/bodorcy/adatb_csaladfa/blob/main/setup.sql) filet

- szükség van egy sql szerverre a **localhost:3306**-os portján

  [*(csaladfa/src/main/resources/application.properties:3)*](https://github.com/bodorcy/adatb_csaladfa/blob/d39991cab3ff1061a294f8ae8810e2d9a46a3644/csaladfa/src/main/resources/application.properties#L3)

- a szerveren kelleni fog egy **family\_tree\_db** nevű database

  [*(csaladfa/src/main/resources/application.properties:3)*](https://github.com/bodorcy/adatb_csaladfa/blob/d39991cab3ff1061a294f8ae8810e2d9a46a3644/csaladfa/src/main/resources/application.properties#L3)

- kell még egy **user** nevű és **user** jelszavú felhasználó, akinek hozzáférése van az előbbi adatbázishoz, illetve rendelkezik a megfelelő módosítási jogokkal

  [*(csaladfa/src/main/resources/application.properties:4-5)*](https://github.com/bodorcy/adatb_csaladfa/blob/d39991cab3ff1061a294f8ae8810e2d9a46a3644/csaladfa/src/main/resources/application.properties#L4)

### Java:
- **IntelliJ Idea**-ban az [adatb_csaladfa/csaladfa/src/main/java/com/CsaladfaApplication.java](https://github.com/bodorcy/adatb_csaladfa/blob/main/csaladfa/src/main/java/com/csaladfa/CsaladfaApplication.java)
osztályban kell futtassuk a main függvényt

- **Terminálban** a *csaladfa-0.0.x-SNAPSHOT.jar* file futtatásával indíthatjuk el az alkalmazást:

```bash
java -jar csaladfa-0.0.1-SNAPSHOT.jar
```

Mindkét esetben a **localhost:8080** url-en megtekinthetjük a weboldalt.
