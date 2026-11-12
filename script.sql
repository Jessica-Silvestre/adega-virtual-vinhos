-- Banco de dados da Adega Virtual (usuários e vinhos)
CREATE DATABASE IF NOT EXISTS bd_adega;
USE bd_adega;

-- Tabela de usuários do sistema (senha guardada em MD5)
CREATE TABLE IF NOT EXISTS usuario(
    usuario varchar(10) primary key,
    nome    varchar(30) default '',
    senha   char(32)    default ''
);

-- Tabela de vinhos (módulo adicional)
CREATE TABLE IF NOT EXISTS vinho(
    nome        varchar(50)   primary key,
    tipo        varchar(10)   not null,
    pais_origem varchar(30)   not null,
    ano         int           not null,
    preco       decimal(10,2) not null,
    quantidade  int           not null
);

-- Usuário de teste: admin / 123
INSERT IGNORE INTO usuario VALUES ('admin', 'Administrador', MD5('123'));

-- Vinho de exemplo
INSERT IGNORE INTO vinho VALUES ('Malbec', 'Tinto', 'Argentina', 2019, 89.90, 12);
