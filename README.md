# Hub

Esta aplicação é responsável pela criação, modificação, listagem e exclusão de contas

# Technologies
  
- Java 8
- Maven 4.0.0 
- Spring Boot 2.0.4.RELEASE
- Spring Fox 2.7.0
- Lombok 1.18.0
- Hibernate Core 5.2.17.Final  
- H2 1.4.194

### Para rodar a aplicação é só seguir os passos abaixo.

- 1 - Entrar na pasta onde está o jar do projeto
- 2 - Executar o seguinte comando pelo cmd

- java -jar hub-0.0.1-SNAPSHOT.jar
- OBS: Este comando da start na aplicação, o mesmo encontra as configurações do arquivo de configuração 
do banco de dados entre outras configurações (application.yml)
   
### Documentação da API (Swagger)
- Link para acesso local: http://localhost:8077/hub-api/v1/swagger-ui.html#/

### H2 SGBD  (Link de acesso)
- Link para acesso local: http://localhost:8077/hub-hub/v1/h2

### Você também pode:

- Solicitação de mesclagem no ramo mestre após ser aplicada na produção
- Todas as histórias precisam se ramificar da homologação
- Toda tarefa precisa de um ramo da história
- A fusão dos ramos das tarefas deve ser feita no equivalente
- Um membro que desenvolveu o história será responsável pela solicitação de mesclagem na homologação

------------------------------------------------------------------------------------------------------------------------------------------
### Banco de dados script sql

CREATE
	SEQUENCE hibernate_sequence
START WITH
	1 INCREMENT BY 1

CREATE
	TABLE
		conta_filial ( id BIGINT NOT NULL,
		account_credit DECIMAL( 19,
		2 ),
		name_account VARCHAR( 255 ),
		start_date TIMESTAMP,
		conta_filial_id BIGINT,
		conta_matriz_id BIGINT,
		legal_person_id BIGINT,
		person_fisica_id BIGINT,
		situacao_id BIGINT,
		PRIMARY KEY ( id ))

CREATE
	TABLE
		conta_matriz ( id BIGINT NOT NULL,
		account_credit DECIMAL( 19,
		2 ),
		name_account VARCHAR( 255 ),
		start_date TIMESTAMP,
		legal_person_id BIGINT,
		person_fisica_entity_id BIGINT,
		situacao_id BIGINT,
		PRIMARY KEY ( id ))

CREATE
	TABLE
		pessoa ( id BIGINT NOT NULL,
		cpf VARCHAR( 255 ),
		date_of_birth DATE,
		name_complete VARCHAR( 255 ),
		PRIMARY KEY ( id ))

CREATE
	TABLE
		pessoa_juridica ( id BIGINT NOT NULL,
		cnpj VARCHAR( 255 ),
		name_of_fantasy VARCHAR( 255 ),
		reason_social VARCHAR( 255 ),
		PRIMARY KEY ( id ))

CREATE
	TABLE
		situacao ( id BIGINT NOT NULL,
		descricao VARCHAR( 255 ),
		PRIMARY KEY ( id ))

ALTER TABLE
	conta_filial ADD CONSTRAINT FKnxmydln41a34haripg47pf82m FOREIGN KEY ( conta_filial_id ) REFERENCES conta_filial

ALTER TABLE
	conta_filial ADD CONSTRAINT FK1nhlj9njj7t7myffofybrgqao FOREIGN KEY ( conta_matriz_id ) REFERENCES conta_matriz
ALTER TABLE
	conta_filial ADD CONSTRAINT FK1p4u87yceche9u9w31av94pg9 FOREIGN KEY ( legal_person_id ) REFERENCES pessoa_juridica
ALTER TABLE
	conta_filial ADD CONSTRAINT FKmbyo08jrqakj8wrkxxah8jwo1 FOREIGN KEY ( person_fisica_id ) REFERENCES pessoa
ALTER TABLE
	conta_filial ADD CONSTRAINT FKh0futjrwv409e7qc3ssv7hh74 FOREIGN KEY ( situacao_id ) REFERENCES situacao
ALTER TABLE
	conta_matriz ADD CONSTRAINT FK1cvtq9iduevbbh39opghk2f8s FOREIGN KEY ( legal_person_id ) REFERENCES pessoa_juridica
ALTER TABLE
	conta_matriz ADD CONSTRAINT FKsby8l9g276pl47cttj96sey0f FOREIGN KEY ( person_fisica_entity_id ) REFERENCES pessoa
ALTER TABLE
	conta_matriz ADD CONSTRAINT FKbsl05cx9jtdwwwvsgso810jqx FOREIGN KEY ( situacao_id ) REFERENCES situacao

INSERT
	INTO
		pessoa_juridica ( cnpj,
		name_of_fantasy,
		reason_social,
		id )
	VALUES ( 07600939000136,
	'VMIX',
	'VMIX',
	1 )
INSERT
	INTO
		pessoa( cpf,
		date_of_birth,
		name_complete,
		id )
	VALUES ( 07600939000136,
	'2018-08-15',
	'Frans',
	1 )

INSERT
	INTO
		CONTA_FILIAL ( ID,
		NAME_ACCOUNT,
		LEGAL_PERSON_ID,
		PERSON_FISICA_ID )
	VALUES( 1,
	'Frans Filial',
	1,
	1 );	
