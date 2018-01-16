INSERT INTO system_user(id,name,surname,email,password,role)
SELECT 1,'Piotr','Dul','dulpiotr@gmail.com','$2a$10$ame.wp8iz6F.Tt1nrc9aGuLuWJIKCXSf1ofFha2PwFQRdqw2v2MWa','administrator'
WHERE NOT EXISTS(SELECT id FROM system_user WHERE id=1);