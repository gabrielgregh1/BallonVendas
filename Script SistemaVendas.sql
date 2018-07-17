create database easySistem;
use easySistem;

create table produto(
codigoProduto int auto_increment primary key ,
controleExtra int,
codigoBarras int,
resumida varchar(50),
completa varchar(250),
dataInclusao  varchar(25),
qtdMinima double,
qtdMaxima double,
qtdEstoque double,
unidadeMedida varchar(50),
valorVenda double,
custoSemImposto double,
custoUnidade double,
lucrativo double,
desconto double,
ncm long,
cest long,
fk_fornecedor int,
preco double,
foreign key(fk_fornecedor) references fornecedor(codigoFornecedor)on delete set null on update set null
);

create table fornecedor(
codigoFornecedor int auto_increment primary key,
nomeFornecedor varchar(30)


);
/*
create table listaTelefonica(
codigoTelefone int auto_increment primary key,
telefone varchar(11),
fk_fornecedor int

);
*/
create table venda(
codigoVenda int primary key auto_increment,
dataVenda varchar(25),
total double,
fk_vendedor int,
foreign key (fk_vendedor) references venda(codigoVendedor)
);

create table produto_vendido(
codigoProdutoVendido int auto_increment primary key,
fk_produto int,
fk_venda int,
qtdProdutoVendido double,
foreign key (fk_produto) references produto(codigoProduto),
foreign key (fk_venda) references venda(codigoVenda)
);
drop table produto_vendido;



create table vendedor(
codigoVendedor int primary key auto_increment,
nome varchar(50)
	
);


create table cliente(
cpf int primary key,
nome varchar(50),
endereco varchar(250)
);

ALTER TABLE vendedor MODIFY codigoVendedor INTEGER NOT NULL AUTO_INCREMENT;

select * from cliente;
select * from vendedor;
select * from produto
order by qtdMaxima desc
LIMIT 5;

select * from venda;
select * from FORNECEDOR;
select * from  produto_vendido;






select resumida, dataVenda,  qtdProdutoVendido, (qtdProdutoVendido*preco) from  produto_vendido, produto, venda
where fk_produto = codigoProduto and fk_venda = codigoVenda;

select resumida, dataVenda,  qtdProdutoVendido, (qtdProdutoVendido*preco) from  produto_vendido, produto, venda
where fk_produto = codigoProduto and fk_venda = codigoVenda 
order by qtdProdutoVendido desc
;


/*
INSERT INTO produto(codigoProduto, controleExtra,codigoBarras, resumida, completa, dataInclusao, qtdMinima, qtdMaxima, qtdEstoque, unidadeMedida, valorVenda, custoSemImposto, custoUnidade, lucrativo, desconto)
 VALUES(default, 1, 1, "Macarrao", "Macarrao doce", now() , 3, 3, 4, "KILO", 5, 3, 4, 3, 1);


drop table venda;

update produto
set controleExtra = 10, codigoBarras = 11
where codigoProduto = 2;

INSERT INTO produto(codigoProduto, controleExtra,codigoBarras, resumida, 
completa, dataInclusao, qtdMinima, qtdMaxima, qtdEstoque,unidadeMedida,
 valorVenda, custoSemImposto, custoUnidade, lucrativo, desconto, ncm, cest, fk_fornecedor)
 VALUES(default, 1, 2, "FRABGO", "GRANISJDS DSIFDJ", now(), 2, 3, 2, "PACAS", 3, 4, 3, 2, 42, 2, 2, 1);



INSERT INTO produto(codigoProduto, controleExtra, codigoBarras, resumida, completa, dataInclusao, qtdMinima, qtdMaxima, qtdEstoque,unidadeMedida, valorVenda, custoSemImposto, custoUnidade, lucrativo
 , desconto, ncm, cest, fk_fornecedor) VALUES(default, 1, 1, "OlaMundo", "OlaMundo Sa", now(), 1, 3, 2, "EURO", 4, 1, 0.5, 10, 99.99, 1, 2, 1);

INSERT INTO VENDA(codigoVenda, dataVenda, total, fk_vendedor) VALUES(default, now(), 4, 2);

UPDATE PRODUTO SET qtdEstoque =  qtdEstoque - 1 WHERE codigoProduto = 2
*/