INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
	VALUES ('read-client', 'resource-server-rest-api',
	/*read-client-password*/'$2a$12$KH4DZ5oN4TlIgebKf.BwW.THegL5wtMDY1lCNzsAj/13pM88EuLL2',
	'read', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
	VALUES ('read-write-client', 'resource-server-rest-api',
	/*read-write-client-password*/'$2a$12$3oZCr4/iJWunLEVWivtqNOtEhcBBuNZIM6zu9FOgUr1XB4YHB1kwi',
	'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);
