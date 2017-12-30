INSERT INTO system_user(name,surname,email,password,role)
 values ('Piotr','Dul','dulpiotr@gmail.com','$2a$10$ame.wp8iz6F.Tt1nrc9aGuLuWJIKCXSf1ofFha2PwFQRdqw2v2MWa','administrator'),
 ('Jan','Kowalski','user@user.com','$2a$10$ame.wp8iz6F.Tt1nrc9aGuLuWJIKCXSf1ofFha2PwFQRdqw2v2MWa','student');
INSERT INTO subject(description,name,last_modified)
 values ('MAT','Matematyka dyskretna',current_date),
 ('AI','Aplikacje internetowe',current_date);
INSERT INTO file(name,url,subject_id,date_added)
  values ('file','lines.txt',1,current_date),
  ('file2','lines2.txt',1,current_date),
  ('file3','lines3.txt',2,current_date);