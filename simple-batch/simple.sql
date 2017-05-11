CREATE TABLE `simple` (
  `seq` int(11) NOT NULL,
  `name` varchar(80) NOT NULL,
  `email` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `simple` (`seq`, `name`, `email`) VALUES
(1, 'kim', 'kim@mail.com'),
(2, 'lee', 'lee@mail.com');


ALTER TABLE `simple`
  ADD PRIMARY KEY (`seq`);


ALTER TABLE `simple`
  MODIFY `seq` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;