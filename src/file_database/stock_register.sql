-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Дек 04 2019 г., 20:49
-- Версия сервера: 10.1.38-MariaDB
-- Версия PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `stocksregister`
--

-- --------------------------------------------------------

--
-- Структура таблицы `stocks`
--

CREATE TABLE `stocks` (
  `amountAuthorizedCapital` bigint(20) NOT NULL,
  `EDRPOU_institutions` varchar(8) NOT NULL,
  `quantity` int(11) NOT NULL,
  `nominalValue` double NOT NULL,
  `totalNominalValue` double NOT NULL,
  `stateDutyPaid` double NOT NULL,
  `releaseDate` date NOT NULL,
  `fullNameOwner` varchar(50) NOT NULL,
  `comment` varchar(10000) NOT NULL,
  `ID` int(11) NOT NULL,
  `allChangeHistory` varchar(10000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `stocks`
--

INSERT INTO `stocks` (`amountAuthorizedCapital`, `EDRPOU_institutions`, `quantity`, `nominalValue`, `totalNominalValue`, `stateDutyPaid`, `releaseDate`, `fullNameOwner`, `comment`, `ID`, `allChangeHistory`) VALUES
(123456, '00001234', 2340, 15.53, 36340.2, 32, '2019-12-03', 'Petraichuk D.K.', 'Ltd.: TATNAFT named after VD Shagina', 1, ' ***  All change history  *** \n--Date 2019-12-03 and time 11:31:34 PM create action: Comment: Ltd.: TATNAFT named after VD Shagina, ID: 0, amount authorized capital: 123456, EDRPOU institutions:00001234, quantity: 2340, total nominal value: 36340.2, nominal value:15.53, state duty paid: 32.0, release date: 2019-12-03, owner: Petrenko P.P., all change history:  ***  All change history  *** \n\n'),
(525000, '00150212', 100000, 10.12, 1012000, 1500, '2019-10-24', 'Dupont J.D.', 'Ltd.: HNCorp named after VD Lukyl', 2, '-'),
(525000, '00001234', 50000, 10.12, 1012000, 760, '2019-10-28', 'Valylev J.D.', 'Ltd.: HNCorp named after VD Lukyl', 3, '-'),
(215000, '00001234', 10000, 30, 3000000, 1200, '2019-11-08', 'Domnskiy A.D.', 'Ltd.: HNCorp named after VD Lukyl', 4, '-'),
(65250, '00001234', 15000, 15, 1500000, 760, '2019-11-28', 'Dronov A.M.', 'Ltd.: HNCorp named after VD Lukyl', 5, '-'),
(51250, '00001234', 20000, 10.12, 1012000, 760, '2019-11-13', 'Marzlyak O.L.', 'Ltd.: HNCorp named after VD Lukyl', 6, '-');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `stocks`
--
ALTER TABLE `stocks`
  ADD UNIQUE KEY `ID` (`ID`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `stocks`
--
ALTER TABLE `stocks`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
