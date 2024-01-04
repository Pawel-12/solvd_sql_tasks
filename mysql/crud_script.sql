USE bank;

INSERT INTO clients(name, surname, phone, monthlyIncome, totalDebt) VALUES
("Adam","Smith","123456789",2000,30000),
("John","Thompson","123123123",4500,50000),
("Mike","Brown","987654321",2500,70000),
("Steve","Johnson","321321321",2000,20000),
("Bob","White","456789123",3000,10000),
("Tom","Baker","111222333",3500,100000);

INSERT INTO consultants(name, surname, phone) VALUES
("Tim","Smith","578832590"),
("Rob","Thompson","895738591"),
("Kevin","Brown","497736012");

INSERT INTO clients_has_consultants VALUES
(1,1),
(2,1),
(3,1),
(4,2),
(5,2),
(6,3);

INSERT INTO accounts(clientID, balance, currency) VALUES
(1,1000,"PLN"),
(2,2000,"USD"),
(3,3000,"GBP"),
(4,4000,"EUR"),
(5,5000,"PLN"),
(6,6000,"USD");

INSERT INTO cardtype(name, cardtype.limit, multicurrency, exchangeFeeRate, creditFeeRate) VALUES
("DebitMulti",0,1,0.05,0),
("Debit",0,0,0,0),
("CreditMulti",9000,1,0.05,0.04),
("Credit",10000,0,0,0.03);

INSERT INTO cards(cardNumber,Accounts_idAccounts, CardType_idCardType, isBlocked, validUntil) VALUES
(1234,1,1,0,'2026-11-14'),
(1243,2,2,0,'2027-11-14'),
(4321,3,3,0,'2028-11-14'),
(3214,4,4,0,'2029-11-14'),
(2143,5,2,1,'2027-05-09'),
(1432,6,4,1,'2026-09-28');

INSERT INTO creditrequeststatus(name) VALUES
("Submited"),
("Reviewed"),
("Accepted"),
("Declined");

INSERT INTO creditrequests(Accounts_idAccounts, CreditRequestStatus_idCreditRequestStatus, Credits_idCredits) VALUES
(6,1,null),
(3,2,null),
(2,3,null),
(1,4,null);

INSERT INTO credits(amount, percentage, paidBackAmount, dueDate, Accounts_idAccounts) VALUES
(100000,0.05,25000,'2030-10-15',2);

INSERT INTO investments(amount, percentage, startDate, endDate, Accounts_idAccounts) VALUES
(5000,0.025,'2023-12-01','2024-03-01',1),
(10000,0.035,'2023-12-01','2024-01-01',2),
(15000,0.04,'2023-12-01','2024-06-01',3);

INSERT INTO mediumtype(mediumtype.name) VALUES
("ATM"),
("CardTransaction"),
("MoneyTransfer");

INSERT INTO transactiontype(transactiontype.name) VALUES
("Income"),
("Outcome");

INSERT INTO transactionpriority(name, priority) VALUES
("Instant", 250),
("NextDay", 180),
("TwoDays", 100),
("OneWeek", 30);

INSERT INTO transactionstatus(name) VALUES
("Pending"),
("Completed"),
("Canceled"),
("Rejected");

INSERT INTO transactions
(account, amount, TransactionType_idTransactionType,
 sendDate, recieveDate, fee, TransactionStatus_idTransactionStatus,
 TransactionPriority_idTransactionPriority, Cards_idCards, MediumType_idMediumType, mediumnumber) 
VALUES
(1,1000,2,'2023-12-21','9999-12-31',5,1,2,1,2,"abc123"),
(2,1500,2,'2023-12-21','9999-12-31',10,4,3,null,3,"accountfrombank2"),
(3,2000,1,'2023-12-21','2023-12-21',15,2,1,3,1,"atmid123");



UPDATE accounts SET balance = 2000 WHERE idAccounts = 1;
UPDATE accounts SET balance = 3000 WHERE idAccounts = 2;
UPDATE accounts SET balance = 4000 WHERE idAccounts = 3;
UPDATE accounts SET balance = 5000 WHERE idAccounts = 4;
UPDATE accounts SET balance = 6000 WHERE idAccounts = 5;
UPDATE accounts SET balance = 7000 WHERE idAccounts = 6;

UPDATE transactions SET recieveDate = date(now()) WHERE sendDate = '9999-12-31' AND TransactionStatus_idTransactionStatus = 2;

UPDATE transactions LEFT JOIN accounts ON account = accounts.idAccounts
SET TransactionStatus_idTransactionStatus = 4 WHERE TransactionStatus_idTransactionStatus = 1 AND accounts.balance < t.amount;

UPDATE cards SET blocked = 0  WHERE cards.Accounts_idAccounts = 5;

UPDATE credits SET paidBackAmount = paidBackAmount + 2500 WHERE Accounts_idAccounts = 2;

UPDATE creditrequests,credits SET Credits_idCredits = credits.idCredits WHERE creditrequests.Accounts_idAccounts = credits.Accounts_idAccounts;



DELETE FROM transactions WHERE transactions.TransactionStatus_idTransactionStatus = 4;

DELETE FROM investments WHERE Accounts_idAccounts = 1;
DELETE FROM investments WHERE Accounts_idAccounts = 2;
DELETE FROM investments WHERE Accounts_idAccounts = 3;

DELETE FROM clients_has_consultants WHERE Consultants_idConsultant = 2;

DELETE FROM accounts WHERE clientID = 1;
DELETE FROM accounts WHERE clientID = 2;
DELETE FROM accounts WHERE clientID = 3;
DELETE FROM accounts WHERE clientID = 4;
DELETE FROM accounts WHERE clientID = 5;



ALTER TABLE transactions RENAME COLUMN TransactionStatus_idTransactionStatus TO transactionStatus;
ALTER TABLE transactions RENAME COLUMN TransactionPriority_idTransactionPriority TO transactionPriority;
ALTER TABLE transactions RENAME COLUMN TransactionType_idTransactionType TO transactionType;

ALTER TABLE transactions MODIFY COLUMN fee float;

ALTER TABLE consultants DROP COLUMN phone, ADD COLUMN email varchar(45);



SELECT * FROM clients as cl 
LEFT JOIN clients_has_consultants as chc ON cl.idClients = chc.Clients_idClients 
LEFT JOIN consultants AS con ON chc.Consultants_idConsultant = con.idConsultant 
LEFT JOIN accounts AS acc ON cl.idClients = acc.clientID
LEFT JOIN cards ON acc.idAccounts = cards.Accounts_idAccounts
LEFT JOIN cardtype ON cards.CardType_idCardType = cardtype.idCardType
LEFT JOIN investments AS inv ON acc.idAccounts = inv.Accounts_idAccounts
LEFT JOIN creditrequests AS crdreq ON acc.idAccounts = crdreq.Accounts_idAccounts
LEFT JOIN creditrequeststatus AS crdreqstatus ON crdreq.CreditRequestStatus_idCreditRequestStatus = crdreqstatus.idCreditRequestStatus
LEFT JOIN credits AS crd ON crdreq.Credits_idCredits = crd.idCredits
LEFT JOIN transactions AS tran ON acc.idAccounts = tran.account
LEFT JOIN transactionpriority AS tranpr ON tran.TransactionPriority_idTransactionPriority = tranpr.idTransactionPriority
LEFT JOIN transactionstatus AS transt ON tran.TransactionStatus_idTransactionStatus = transt.idTransactionStatus
LEFT JOIN transactiontype AS trantp ON tran.TransactionType_idTransactionType = trantp.idTransactionType
LEFT JOIN mediumtype AS mt ON tran.MediumType_idMediumType = mt.idMediumType;

SELECT * FROM creditrequests CROSS JOIN credits;
SELECT * FROM creditrequests LEFT JOIN credits on creditrequests.Credits_idCredits = credits.idCredits;
SELECT * FROM creditrequests RIGHT JOIN credits on creditrequests.Credits_idCredits = credits.idCredits;
SELECT * FROM creditrequests INNER JOIN credits on creditrequests.Credits_idCredits = credits.idCredits;


SELECT Consultants_idConsultant, count(*) from clients_has_consultants GROUP BY Consultants_idConsultant;
SELECT Clients_idClients, count(*) from clients_has_consultants GROUP BY Clients_idClients;
SELECT account, max(amount) from transactions GROUP BY account;
SELECT currency, avg(balance) from accounts GROUP BY currency;
SELECT currency, sum(balance) from accounts GROUP BY currency;
SELECT currency, count(balance) from accounts GROUP BY currency;
SELECT CardType_idCardType, count(CardType_idCardType) FROM cards GROUP BY CardType_idCardType;


SELECT currency, avg(balance) from accounts GROUP BY currency HAVING currency = "PLN";
SELECT currency, sum(balance) from accounts GROUP BY currency HAVING currency = "USD";
SELECT currency, count(balance) from accounts GROUP BY currency HAVING currency = "EUR";
SELECT CardType_idCardType, isBlocked, count(CardType_idCardType) FROM cards GROUP BY CardType_idCardType, isBlocked HAVING isBlocked = 0;
SELECT min(balance) FROM accounts GROUP BY currency HAVING currency = "PLN";
SELECT max(amount) FROM transactions GROUP BY TransactionType_idTransactionType HAVING TransactionType_idTransactionType = 2;
SELECT endDate, max(amount) FROM investments GROUP BY endDate HAVING endDate <= "2024-03-01";
