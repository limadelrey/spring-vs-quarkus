CREATE TABLE IF NOT EXISTS book ( id     UUID
                                , author VARCHAR(255) NOT NULL
                                , title  VARCHAR(255) NOT NULL
                                , price  DECIMAL(8,2) NOT NULL
                                , PRIMARY KEY(id)
                                );