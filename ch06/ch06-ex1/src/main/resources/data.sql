INSERT INTO `user` (`id`, `username`, `password`, `algorithm`) VALUES ('1', 'john', '{bcrypt}$2a$12$pgwL5hi18/RKlDsP/RHRuO/eh6PsZCobuaK5X/fsKpohscgXiCpx6', 'BCRYPT');

INSERT INTO `authority` (`id`, `name`, `user`) VALUES ('1', 'READ', '1');
INSERT INTO `authority` (`id`, `name`, `user`) VALUES ('2', 'WRITE', '1');

INSERT INTO `product` (`id`, `name`, `price`, `currency`) VALUES ('1', 'Chocolate', '10', 'USD');