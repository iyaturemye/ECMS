-- phpMyAdmin SQL Dump
-- version 
-- http://www.phpmyadmin.net
--
-- Host: mysql3000.mochahost.com
-- Generation Time: Mar 14, 2019 at 05:40 AM
-- Server version: 5.6.23
-- PHP Version: 5.6.30

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `claude_garage`
--
CREATE DATABASE `claude_garage` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `claude_garage`;

-- --------------------------------------------------------

--
-- Table structure for table `Bidding`
--

CREATE TABLE IF NOT EXISTS `Bidding` (
  `bidId` varchar(255) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `estimatedDate` datetime DEFAULT NULL,
  `isApproved` bit(1) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `totalPrice` double NOT NULL,
  `garageId` varchar(255) DEFAULT NULL,
  `vehicleDetailsId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bidId`),
  KEY `FK_h5v4huernacynbc9vn5wqxejo` (`garageId`),
  KEY `FK_n3tqlgty4b2w8ad08rydujfcx` (`vehicleDetailsId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Bidding`
--

INSERT INTO `Bidding` (`bidId`, `comment`, `createAt`, `estimatedDate`, `isApproved`, `status`, `totalPrice`, `garageId`, `vehicleDetailsId`) VALUES
('441fe734-d24e-4c9d-a4cf-b79ecacc9861', '', '2019-03-09 07:40:55', '2019-04-10 02:50:00', '\0', 'unread', 0, '8b61d3f7-b312-420b-b8b5-d5d721a540f7', '3ffb4a5b-8b83-4ea1-b541-50e17f83b8ff'),
('606273cd-9d47-4f38-a164-8aa55adee3a2', '', '2019-03-09 07:28:47', '2019-03-05 02:05:20', '\0', 'unread', 0, '8b61d3f7-b312-420b-b8b5-d5d721a540f7', '75d4e9bc-b0d9-47b1-b7b1-814b900a27d5'),
('4c692baa-01b4-40a3-beaa-29130b9914ec', '', '2019-03-09 07:27:22', '2019-03-14 06:30:00', '\0', 'read', 0, '50cc32c1-551c-4eef-83db-7ea83cf2fd45', '75d4e9bc-b0d9-47b1-b7b1-814b900a27d5'),
('ccbedf16-00b2-4f1f-8ee5-47a44430a9a4', 'well \r\ntry ', '2019-03-09 07:26:46', '2019-04-03 05:50:44', '', 'completed', 0, '285125c3-b326-429e-ac10-814fef6b66bb', '75d4e9bc-b0d9-47b1-b7b1-814b900a27d5'),
('d4659f06-73fa-4e80-989e-42be5d991733', '', '2019-03-09 07:09:05', '2019-03-23 00:00:00', '', 'completed', 0, '285125c3-b326-429e-ac10-814fef6b66bb', 'ebac1eb2-ed6f-4471-bbd8-a4885c2ae6a9');

-- --------------------------------------------------------

--
-- Table structure for table `BrokenCarPart`
--

CREATE TABLE IF NOT EXISTS `BrokenCarPart` (
  `id` varchar(255) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `partNumber` varchar(255) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `carsparepart_id` varchar(255) DEFAULT NULL,
  `vehicleDetails_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mc2xihrkm1sjw8rf9womxmh0h` (`carsparepart_id`),
  KEY `FK_rtyhq4ee6t17fgfr1jighqhuo` (`vehicleDetails_uuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `BrokenCarPart`
--

INSERT INTO `BrokenCarPart` (`id`, `createdAt`, `partNumber`, `quantity`, `carsparepart_id`, `vehicleDetails_uuid`) VALUES
('31250d6a-cbb2-475a-ab93-a79f93233d37', '2019-03-02 06:59:24', 'dddd', 2, '4189b48c-ebf8-4a5f-bda6-b913f173476e', '390f7c15-0ad8-4255-9413-9d13fa689470'),
('1170e9ce-0d4c-453c-b9f1-eaae1b9c3d4c', '2019-03-02 06:20:29', 'jjjjj', 1, '0fb71508-7715-49b4-8c54-384d6c7bc271', 'b653a06a-7e3e-4350-a407-724f7a1ef7d9'),
('91a52e6d-5eaf-451c-8511-050b9a13ccfc', '2019-03-02 06:21:45', 'hhhhh', 3, 'dd835796-4080-4a10-aee5-620a0a72af1d', 'b653a06a-7e3e-4350-a407-724f7a1ef7d9'),
('61f888bc-40e9-4df3-8a14-cc02fdb1232e', '2019-03-09 03:54:43', '', 5, '0fb71508-7715-49b4-8c54-384d6c7bc271', 'c54f747c-3cbc-4055-bf0a-6c2d6ec14183'),
('34516384-66c6-4529-b93a-84c4e818a810', '2019-03-09 03:55:58', '', 1, 'dd835796-4080-4a10-aee5-620a0a72af1d', 'c54f747c-3cbc-4055-bf0a-6c2d6ec14183'),
('09ac4117-594d-461a-987a-f59ff2888510', '2019-03-09 03:56:55', '', 3, 'afed4f6c-fd3b-42f3-aab1-898436fd8b37', 'c54f747c-3cbc-4055-bf0a-6c2d6ec14183'),
('dd6caa89-a816-47da-b090-793729696252', '2019-03-09 03:56:07', '', 3, '539783c2-0e19-4970-bff4-2cfde553057e', 'c54f747c-3cbc-4055-bf0a-6c2d6ec14183'),
('637be447-8e55-45bd-9d6e-0b39d559cbd6', '2019-03-09 03:56:29', '', 22, 'c8959d1b-df50-45e7-9c1a-bf748f915d04', 'c54f747c-3cbc-4055-bf0a-6c2d6ec14183'),
('c542bd31-5d92-476f-bada-582ed57a208d', '2019-03-09 03:55:43', '', 4, '4189b48c-ebf8-4a5f-bda6-b913f173476e', 'c54f747c-3cbc-4055-bf0a-6c2d6ec14183'),
('8157bdcb-b637-41ae-a519-aa3037556e28', '2019-03-09 03:56:48', '', 2, '0fb71508-7715-49b4-8c54-384d6c7bc271', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('44582f85-ea0d-45ef-b455-28b03af2e195', '2019-03-09 03:58:13', '', 2, '20c0c01d-323e-4a8f-9480-492bfc98544d', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('4fb32177-ec7e-4cf9-a822-a513db7302c0', '2019-03-09 03:58:02', '', 2, 'c8959d1b-df50-45e7-9c1a-bf748f915d04', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('575c5b06-75d3-4bf6-9c2b-70aa1e4f9d03', '2019-03-09 04:00:00', '', 0, '92bd0e67-577f-4fb0-aa34-51a26dd13ab0', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('98f1adc6-de8f-4e77-a4e1-02bd3915eb2c', '2019-03-09 03:57:54', '', 1, '8d2ea527-0b4a-41a7-87b4-41eb40b49186', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('8e33206a-45c8-4131-acae-34ab85c5f96d', '2019-03-09 03:58:48', '', 3, '32c7923f-5ea8-453b-8d4c-674ba5c64308', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('d480fa3c-4e90-4338-b141-6ad22b186df8', '2019-03-09 03:59:11', '', 2, '2d3eb35f-2d6a-4c7c-9b80-d64a98fe3470', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('3aa39289-f588-45fd-9c0a-7a6e197d1832', '2019-03-09 03:56:36', '', 5, '4189b48c-ebf8-4a5f-bda6-b913f173476e', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('b9d3861e-dfcc-4157-a0f6-243fa5aa91e3', '2019-03-09 03:56:57', '', 2, '09d6d9af-3cee-447c-94ac-55294c9f5d76', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('7f1b660e-8b2b-49df-9fa6-923b52891802', '2019-03-09 03:59:01', '', 1, 'f7bea8b6-0c66-4f39-8b61-e9ede18426df', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('137a9b1b-eb19-4ca7-8aba-06c2655eda84', '2019-03-09 03:56:04', '', 3, 'dd835796-4080-4a10-aee5-620a0a72af1d', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('9d2a39be-af69-4ac0-8503-23b0ba307287', '2019-03-09 04:00:07', '', 2, 'b526d62d-d0a4-4aa7-a653-7acf1ebe578f', 'aa0ce80e-ca55-4105-a823-f7cb5629365f'),
('ae32ea5f-da78-4ea9-9929-3194ede716c6', '2019-03-09 04:01:12', 'xx4545b', 3, '4189b48c-ebf8-4a5f-bda6-b913f173476e', '396b1019-c3fc-4a20-897f-78cf35d5e30b'),
('31b6ee36-db23-42bc-bd45-603d14070f16', '2019-03-09 04:00:54', '21245x', 5, '0fb71508-7715-49b4-8c54-384d6c7bc271', '396b1019-c3fc-4a20-897f-78cf35d5e30b'),
('1ac9b5f9-1097-43a8-b1ca-90b0dac36a84', '2019-03-09 04:01:28', 'm,h/jh7', 2, '286e8b95-6b71-48ef-84e0-3c7163903abe', '396b1019-c3fc-4a20-897f-78cf35d5e30b'),
('9dcb6ddf-f25a-4afc-9a06-b1e7f34007bf', '2019-03-09 03:53:53', '', 5, '0fb71508-7715-49b4-8c54-384d6c7bc271', '514da2ca-7da4-4ec8-8468-2b8a5a200a3a'),
('c60dc6f0-c624-4e0a-9926-c5242506cbfe', '2019-03-09 04:01:57', '', 3, '4189b48c-ebf8-4a5f-bda6-b913f173476e', '514da2ca-7da4-4ec8-8468-2b8a5a200a3a'),
('1ea4bd6c-c88d-4ef1-966a-e51f5ea5f142', '2019-03-09 04:06:03', '', 2, '0fb71508-7715-49b4-8c54-384d6c7bc271', 'c60ce603-a52f-4f04-99db-3983658ec2eb'),
('66fa8ed3-9f36-4b5c-882f-0e0b4befbb9a', '2019-03-09 04:06:28', '', 0, 'dd835796-4080-4a10-aee5-620a0a72af1d', 'c60ce603-a52f-4f04-99db-3983658ec2eb'),
('f1a64ab9-ba77-42de-b236-165af3895b10', '2019-03-09 04:06:32', '', 8, '0fa8b0d9-e691-47c2-9b96-770801cb3283', 'c60ce603-a52f-4f04-99db-3983658ec2eb'),
('4371b883-68d3-48fb-8bfa-b73054fc2637', '2019-03-09 04:06:49', '', 2, 'afed4f6c-fd3b-42f3-aab1-898436fd8b37', 'c60ce603-a52f-4f04-99db-3983658ec2eb'),
('cb91a3ac-3dce-49fe-8265-5a4e36a0dae5', '2019-03-09 04:07:01', '', 4, 'e9e952a3-33f4-4ee3-b92a-9b2c648cbdd1', 'c60ce603-a52f-4f04-99db-3983658ec2eb'),
('04939c6b-c4c4-4799-a68e-0747c7af5319', '2019-03-09 04:06:19', '', 5, '4189b48c-ebf8-4a5f-bda6-b913f173476e', 'c60ce603-a52f-4f04-99db-3983658ec2eb'),
('9535a606-1b5c-4479-b994-18461cd61e56', '2019-03-09 06:06:03', 'xx2', 2, '0fb71508-7715-49b4-8c54-384d6c7bc271', '9d00606b-c274-493f-978f-4f99b472758e'),
('ea5ca368-38f8-4002-94c2-c87161895eb9', '2019-03-09 06:06:53', '', 9, '20c0c01d-323e-4a8f-9480-492bfc98544d', '9d00606b-c274-493f-978f-4f99b472758e'),
('92799632-d313-47bc-ad34-6a9ecd86b5fc', '2019-03-09 06:06:24', '', 25, 'c71387e0-31bd-4f6e-8449-2009758a1e8c', '9d00606b-c274-493f-978f-4f99b472758e'),
('f29dcdc7-60bf-4d5c-8231-0cfc34a38812', '2019-03-09 06:06:41', '', 10, 'de84c918-634a-49ae-9d1a-6f5b4b63303e', '9d00606b-c274-493f-978f-4f99b472758e'),
('53ee89a8-a483-461b-9ebc-afc8f7acd77c', '2019-03-09 06:07:18', '', 7, '32c7923f-5ea8-453b-8d4c-674ba5c64308', '9d00606b-c274-493f-978f-4f99b472758e'),
('f0e154d2-4c49-4459-a4d3-1ab8d3c50642', '2019-03-09 06:07:25', 'll6t', 5, 'f7bea8b6-0c66-4f39-8b61-e9ede18426df', '9d00606b-c274-493f-978f-4f99b472758e'),
('3065bba7-e203-45eb-b3fd-5556c5b8540b', '2019-03-09 06:47:28', '', 10, '0fb71508-7715-49b4-8c54-384d6c7bc271', '4b18d1c4-a465-487f-9471-ce085e6cb69e'),
('425bd197-6757-4eca-9b91-e3e7aa91d1d1', '2019-03-09 06:47:59', '', 1, 'dd835796-4080-4a10-aee5-620a0a72af1d', '4b18d1c4-a465-487f-9471-ce085e6cb69e'),
('1d768756-6b35-4b6e-824b-fee0596f2269', '2019-03-09 06:47:50', '', 3, '4189b48c-ebf8-4a5f-bda6-b913f173476e', '4b18d1c4-a465-487f-9471-ce085e6cb69e'),
('506cd759-f48c-47cb-beeb-7c3851f4ea1e', '2019-03-09 06:53:55', '', 4, '0fb71508-7715-49b4-8c54-384d6c7bc271', 'ebac1eb2-ed6f-4471-bbd8-a4885c2ae6a9'),
('a6348312-55b1-4c85-9f46-f253867f52ca', '2019-03-09 06:53:41', '', 3, 'dd835796-4080-4a10-aee5-620a0a72af1d', 'ebac1eb2-ed6f-4471-bbd8-a4885c2ae6a9'),
('ac356387-ddd5-4e06-9e12-960bae38db0c', '2019-03-09 07:14:54', 'rt456t', 7, '33604104-b58e-418f-b411-50f2f1ce9efc', '75d4e9bc-b0d9-47b1-b7b1-814b900a27d5'),
('0f9f1e2f-d9f2-4be2-8bc8-d7cf503968b6', '2019-03-09 07:15:35', 'TTRE3', 2, 'afed4f6c-fd3b-42f3-aab1-898436fd8b37', '75d4e9bc-b0d9-47b1-b7b1-814b900a27d5'),
('15159618-0a7b-4c47-afec-4672f542e85f', '2019-03-09 07:15:07', 'Ddsa5', 2, '0fa8b0d9-e691-47c2-9b96-770801cb3283', '75d4e9bc-b0d9-47b1-b7b1-814b900a27d5'),
('0b241a67-2f9e-4daf-bb22-0e8d311910ef', '2019-03-09 07:14:40', '', 7, '32c4c2e2-806d-4cb8-bedf-f99c062c43f9', '75d4e9bc-b0d9-47b1-b7b1-814b900a27d5'),
('1540e410-615d-41fa-8e2a-c6e2adfa3044', '2019-03-09 07:14:23', '', 2, '0fb71508-7715-49b4-8c54-384d6c7bc271', '75d4e9bc-b0d9-47b1-b7b1-814b900a27d5'),
('d772053d-8695-41eb-a72b-260bfaaa10f0', '2019-03-09 07:16:26', 'XX2', 2, '0fa8b0d9-e691-47c2-9b96-770801cb3283', '3ffb4a5b-8b83-4ea1-b541-50e17f83b8ff'),
('7a3ea9c4-784e-4b70-8f87-82b196da36c3', '2019-03-09 07:16:49', 'JUI', 9, 'e9e952a3-33f4-4ee3-b92a-9b2c648cbdd1', '3ffb4a5b-8b83-4ea1-b541-50e17f83b8ff'),
('f4e8e95e-d775-4b6b-b282-52a85118227e', '2019-03-09 07:15:51', 'V45', 6, 'dd835796-4080-4a10-aee5-620a0a72af1d', '3ffb4a5b-8b83-4ea1-b541-50e17f83b8ff'),
('9011e97c-9d0b-46f8-a02f-8b793dc32345', '2019-03-09 07:16:50', '', 6, '539783c2-0e19-4970-bff4-2cfde553057e', '4b18d1c4-a465-487f-9471-ce085e6cb69e'),
('862ec193-a872-4b5a-9588-9c81a2ff507c', '2019-03-09 07:16:33', '', 3, 'dd835796-4080-4a10-aee5-620a0a72af1d', '4b18d1c4-a465-487f-9471-ce085e6cb69e'),
('fec6e4ad-168a-4a53-bb25-3b7c6d104892', '2019-03-09 07:15:17', '', 2, '0fb71508-7715-49b4-8c54-384d6c7bc271', '4b18d1c4-a465-487f-9471-ce085e6cb69e'),
('4b423bd4-cda1-4246-b2f3-308123e3acff', '2019-03-09 07:16:20', '', 52, '4189b48c-ebf8-4a5f-bda6-b913f173476e', '4b18d1c4-a465-487f-9471-ce085e6cb69e'),
('e98c5c65-d946-4593-8831-5d9385bb367d', '2019-03-09 07:17:26', '', 2, '7567d12f-26d9-40d3-a02d-c296b39fcb86', '4b18d1c4-a465-487f-9471-ce085e6cb69e'),
('1214d318-e629-42a8-b614-c6ea4b5e4c61', '2019-03-09 07:17:17', '', 5, '5ded79a1-3ad9-4b27-9243-550ff8ee842f', '4b18d1c4-a465-487f-9471-ce085e6cb69e'),
('dc66a1c1-e02d-44fb-a393-6c9589f80916', '2019-03-09 07:17:38', '', 3, '2d3eb35f-2d6a-4c7c-9b80-d64a98fe3470', '4b18d1c4-a465-487f-9471-ce085e6cb69e'),
('2d31bf24-ca83-4d3f-bcc5-ad6b369af4ad', '2019-03-09 07:18:08', '', 3, '2d3eb35f-2d6a-4c7c-9b80-d64a98fe3470', '4b18d1c4-a465-487f-9471-ce085e6cb69e');

-- --------------------------------------------------------

--
-- Table structure for table `Carsparepart`
--

CREATE TABLE IF NOT EXISTS `Carsparepart` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c5ca5013o123s1k996x6i8eqi` (`parent`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Carsparepart`
--

INSERT INTO `Carsparepart` (`id`, `name`, `parent`) VALUES
('03c648a3-34bf-4043-9c25-0ef58301363e', 'electrical1', '33476076-60e5-4ac9-af2b-2a8513b6d224'),
('09d6d9af-3cee-447c-94ac-55294c9f5d76', 'Mastic', '0bfd0dfc-87fb-47f8-8722-e5342d2991e1'),
('0bfd0dfc-87fb-47f8-8722-e5342d2991e1', 'UNDER FRAME', NULL),
('0fa8b0d9-e691-47c2-9b96-770801cb3283', 'wheels2', 'dabdaa60-7791-4c7e-b449-80af5d2147ac'),
('0fb71508-7715-49b4-8c54-384d6c7bc271', 'under2', '0bfd0dfc-87fb-47f8-8722-e5342d2991e1'),
('18ad67a0-d017-40f3-be47-c6ac513f5faf', 'engine2', 'bd044a59-aa04-4a41-bb91-9054ea26432a'),
('20c0c01d-323e-4a8f-9480-492bfc98544d', 'steering2', 'cda8135d-27fa-40df-828e-ca98a4eb78e3'),
('26f702a8-816b-4330-a749-ecf7b82a9f5b', 'instruments3', 'e954addc-daac-4463-a647-6433ead9c178'),
('286e8b95-6b71-48ef-84e0-3c7163903abe', 'coolant3', '714df917-f884-431f-8986-b5c97020dfb3'),
('28c93095-062c-48e2-b960-e6ab5adc9d52', 'FUEL SYSTEM', NULL),
('2d3eb35f-2d6a-4c7c-9b80-d64a98fe3470', 'body 3', 'cb30491a-2146-4ddd-bf14-4edd990b8ad8'),
('303f5abe-36d0-4da4-ab46-5e62e84a5dff', 'break3', 'bef86769-233c-4a9f-b8a0-a3edf7800a0b'),
('32c4c2e2-806d-4cb8-bedf-f99c062c43f9', 'gear3', '8d77047c-b45c-4eb9-a11b-ae4e9b45ce9f'),
('32c7923f-5ea8-453b-8d4c-674ba5c64308', 'steering1', 'cda8135d-27fa-40df-828e-ca98a4eb78e3'),
('33476076-60e5-4ac9-af2b-2a8513b6d224', 'ELECTRICAL', NULL),
('33604104-b58e-418f-b411-50f2f1ce9efc', 'gear2', '8d77047c-b45c-4eb9-a11b-ae4e9b45ce9f'),
('4189b48c-ebf8-4a5f-bda6-b913f173476e', 'under1', '0bfd0dfc-87fb-47f8-8722-e5342d2991e1'),
('500a4e9f-1a3c-449c-a3b6-deec46c9829d', 'break1', 'bef86769-233c-4a9f-b8a0-a3edf7800a0b'),
('539783c2-0e19-4970-bff4-2cfde553057e', 'clutch1', '6113e5e6-4cf0-4e6a-8d89-aca1fd294bf8'),
('59c99f16-d5f4-436c-8333-244de3e3d9e5', 'instruments1', 'e954addc-daac-4463-a647-6433ead9c178'),
('5ded79a1-3ad9-4b27-9243-550ff8ee842f', 'cluth2', '6113e5e6-4cf0-4e6a-8d89-aca1fd294bf8'),
('60718987-3870-4541-996c-5e3c7f568137', 'instruments2', 'e954addc-daac-4463-a647-6433ead9c178'),
('6113e5e6-4cf0-4e6a-8d89-aca1fd294bf8', 'CLUTCH', NULL),
('647b102e-1186-4cef-bb6c-c3a6d0454ad5', 'break2', 'bef86769-233c-4a9f-b8a0-a3edf7800a0b'),
('714df917-f884-431f-8986-b5c97020dfb3', 'COOLANT SYSTEM', NULL),
('7567d12f-26d9-40d3-a02d-c296b39fcb86', 'cluth3', '6113e5e6-4cf0-4e6a-8d89-aca1fd294bf8'),
('761fa09c-c745-4019-8e87-1a70ba85e1df', 'coolant2', '714df917-f884-431f-8986-b5c97020dfb3'),
('81cda540-1566-44dc-84fc-959b2ef32c01', 'engine3', 'bd044a59-aa04-4a41-bb91-9054ea26432a'),
('8d2ea527-0b4a-41a7-87b4-41eb40b49186', 'fuel 3', '28c93095-062c-48e2-b960-e6ab5adc9d52'),
('8d77047c-b45c-4eb9-a11b-ae4e9b45ce9f', 'GEARBOX/FINAL DRIVE', NULL),
('92bd0e67-577f-4fb0-aa34-51a26dd13ab0', 'body 2', 'cb30491a-2146-4ddd-bf14-4edd990b8ad8'),
('99985dc2-4452-40f8-bd38-0d893504f9c7', 'gear1', '8d77047c-b45c-4eb9-a11b-ae4e9b45ce9f'),
('a8ca27b2-5963-4f7d-b10b-0994f8eef23c', 'engine1', 'bd044a59-aa04-4a41-bb91-9054ea26432a'),
('afed4f6c-fd3b-42f3-aab1-898436fd8b37', 'wheels1', 'dabdaa60-7791-4c7e-b449-80af5d2147ac'),
('b526d62d-d0a4-4aa7-a653-7acf1ebe578f', 'body 1', 'cb30491a-2146-4ddd-bf14-4edd990b8ad8'),
('bd044a59-aa04-4a41-bb91-9054ea26432a', 'ENGINE & EMISSIONS', NULL),
('bef86769-233c-4a9f-b8a0-a3edf7800a0b', 'BREAKING', NULL),
('c41c2c6a-157d-4a41-b2c9-0024d80dcfae', 'GEARBOX/FINAL DRIVE', NULL),
('c71387e0-31bd-4f6e-8449-2009758a1e8c', 'electrical2', '33476076-60e5-4ac9-af2b-2a8513b6d224'),
('c8959d1b-df50-45e7-9c1a-bf748f915d04', 'fuel1', '28c93095-062c-48e2-b960-e6ab5adc9d52'),
('cb30491a-2146-4ddd-bf14-4edd990b8ad8', 'Body', NULL),
('cda8135d-27fa-40df-828e-ca98a4eb78e3', 'STEERING & SUSPENSION', NULL),
('dabdaa60-7791-4c7e-b449-80af5d2147ac', 'WHEELS & TYRES', NULL),
('dd835796-4080-4a10-aee5-620a0a72af1d', 'under3', '0bfd0dfc-87fb-47f8-8722-e5342d2991e1'),
('de84c918-634a-49ae-9d1a-6f5b4b63303e', 'electrical3', '33476076-60e5-4ac9-af2b-2a8513b6d224'),
('e954addc-daac-4463-a647-6433ead9c178', 'INSTRUMENTS & OTHERS', NULL),
('e9e952a3-33f4-4ee3-b92a-9b2c648cbdd1', 'wheels3', 'dabdaa60-7791-4c7e-b449-80af5d2147ac'),
('f6e98d39-d7cd-4be6-a934-fe5dc935c08a', 'coolant1', '714df917-f884-431f-8986-b5c97020dfb3'),
('f7bea8b6-0c66-4f39-8b61-e9ede18426df', 'steering3', 'cda8135d-27fa-40df-828e-ca98a4eb78e3');

-- --------------------------------------------------------

--
-- Table structure for table `Client`
--

CREATE TABLE IF NOT EXISTS `Client` (
  `clientId` varchar(255) NOT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`clientId`),
  KEY `FK_evltrvdpchyrko5ydc1o26cmh` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Client`
--

INSERT INTO `Client` (`clientId`, `fname`, `lname`, `phoneNumber`, `userId`) VALUES
('9222c8b8-6119-4f2d-9fa6-7c13947dfed5', 'Muhoza ', 'Adeline', '+250 722 320 5680', '5e9ebbbf-7128-4877-ae86-579dc7b8d85a'),
('ce1b0c46-72db-4d56-a40c-a001c71f658e', 'isaac', 'uwizeyimana', '+250 788 201 286', 'c78b7614-6d45-40f2-937d-abd697afe5de'),
('7e07dd14-3dd0-488e-9e05-f9128312f361', 'iyaturemye ', 'claude', '+250 722 320 568', 'af337330-d926-487d-bc03-41ff0826e62b'),
('69d80b24-d1d3-44b0-a97f-458d847cb8ed', 'Prince', 'Jean', '+250 786 546 056', '34d8c609-516f-475b-8cfd-a9dcb1415986'),
('e74be3b8-1ef7-4ce5-bc19-5c211da576b4', 'Nsengiyumva', 'Sylvain', '+250 788 210 354', '83f94093-071f-45a7-9dc1-a976d06e56f9'),
('cfda06f5-753e-43f9-97b5-7329f048e78c', 'MUKESHIMANA', 'NESTA YVONNE', '+250 788 393 798', '52c7e1ff-d96d-406a-b7d1-d9248d4b565d'),
('b4c41e0e-1240-40c4-83d7-9b890165c12b', 'kaneza', 'aime', '+250 785 000 134', '44e8bbeb-88f9-49e3-a746-355f43c5dc82'),
('4478b297-fcea-468c-8286-6ea55adb51e8', 'Iyaturemye', 'Claude', '+250 722 320 5680', '5b601ead-83e2-4598-8242-862e4e556213'),
('91c528ee-670d-4500-9345-b623f75251d8', 'Nsengiyumva', 'Sylvain', '+250 788 210 354', 'bf51f6c4-3b36-4b87-9613-572b45776c6d'),
('26f1e84f-4563-41d6-9949-cc27b81ac486', 'MUKESHIMANA', 'NESTA YVONNE', '+250 788 393 798', 'ecc5685a-29f1-43a2-a625-44e6e5b882dd'),
('7dc0fced-beb2-4d8b-8533-f0a878bd5473', 'Prince', 'Jean', '+250 738 485 419', '7b10e799-ef36-4ea2-bd1c-89ecea1eba09'),
('66c1723b-423a-424c-8359-fcb559e38387', 'isaac', 'uwizeyimana', '+250 788 201 286', '6e21fedc-e001-4ecd-b71a-0511dc2393e7'),
('39e113b3-6724-4381-a952-e7ae50b145a4', 'MUKESHIMANA', 'NESTA YVONNE', '+250 788 393 798', '072dcb59-51b8-4900-9217-db686e3a38b8'),
('192aa236-b8d2-439d-99d0-7f7c171b22d7', 'cyuzuzo', 'emma', '+250 785 000 134', '2e725cf4-4619-4235-a1a7-a3fa83877c49'),
('ff3b52d5-b706-4c7f-8036-0a39feef683a', 'gikundiro', 'isaac', '+250 788 201 286', 'b37e1d1e-804b-470d-85f1-e86cad4a86cf'),
('80fba265-ce4b-4a17-8b26-cfd831caaeb0', 'cyiza', 'jeremi', '+250 785 259 109', '93ddfd13-f8e3-420f-8101-a822ada2029d'),
('589391c9-ce7a-4f19-9ffe-b168cf270759', 'karambizi', 'Innocent', '+250 788 301 999', 'fdc145a6-5c02-4822-a74d-eb2fcb2e61d8');

-- --------------------------------------------------------

--
-- Table structure for table `CompletedCar`
--

CREATE TABLE IF NOT EXISTS `CompletedCar` (
  `purchaseOrdernum` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatedAt` datetime DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `isPaid` bit(1) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updatedAt` date DEFAULT NULL,
  `bidding` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`purchaseOrdernum`),
  KEY `FK_ga180usrb2m5jdpx5w4hrbpa4` (`bidding`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `CompletedCar`
--

INSERT INTO `CompletedCar` (`purchaseOrdernum`, `CreatedAt`, `comment`, `isPaid`, `status`, `updatedAt`, `bidding`) VALUES
(1, '2019-03-09 07:12:25', 'all things are done now you can come and take it', '\0', 'unread', '2019-03-09', 'd4659f06-73fa-4e80-989e-42be5d991733'),
(2, '2019-03-09 07:31:40', 'work Done', '', 'unread', '2019-03-09', 'ccbedf16-00b2-4f1f-8ee5-47a44430a9a4');

-- --------------------------------------------------------

--
-- Table structure for table `Driver`
--

CREATE TABLE IF NOT EXISTS `Driver` (
  `id` varchar(255) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nationalId` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_huvy4ihwafh1wbt236gj8fwtu` (`nationalId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ExpectiseGarage`
--

CREATE TABLE IF NOT EXISTS `ExpectiseGarage` (
  `uuid` varchar(255) NOT NULL,
  `createAt` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `garageId` varchar(255) DEFAULT NULL,
  `insuranceId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FK_7y8i26903lvwxg81pus2bittn` (`garageId`),
  KEY `FK_k9ilvcaw189hhx57qy3gqs064` (`insuranceId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ExpectiseGarage`
--

INSERT INTO `ExpectiseGarage` (`uuid`, `createAt`, `status`, `garageId`, `insuranceId`) VALUES
('65b795dd-20a6-429f-a9d4-56a43609a27b', '2019-02-14 06:26:47', 'active', '6a4fa01d-64d4-4266-9204-16f6181e54b8', '739e747e-aafe-448d-85cb-24159e21c1af'),
('dcaacee2-4fe5-40b7-8b56-7644fc3a1697', '2019-02-14 06:26:53', 'active', 'a4b7d3c4-a20c-489b-9a2b-817d212502bc', '739e747e-aafe-448d-85cb-24159e21c1af'),
('c2d43859-caf7-40b6-8d1e-90e8d9305e2f', '2019-03-02 03:00:58', 'active', 'da6316b8-767b-4800-bf3b-e75d5c162826', '65611e0b-c6ac-4e7f-8ac9-97ef08de1dbf'),
('ff5ce20d-583f-4515-a3e7-7f30cb9ed6c9', '2019-03-02 03:01:32', 'active', '60858d1f-8c4f-4319-b44a-82f2cf95a417', '65611e0b-c6ac-4e7f-8ac9-97ef08de1dbf'),
('5e0e5e1d-d95e-42fb-85da-aa646aafb73d', '2019-03-02 03:01:35', 'active', 'da6316b8-767b-4800-bf3b-e75d5c162826', '1f58d443-55f7-44ad-afc8-576d55ea2aea'),
('01a7f9b0-c5f6-4fc4-acfe-0038e99d0222', '2019-03-02 03:01:35', 'active', '8b7e3eb2-ba9a-4505-b9aa-c65c9c718e5d', 'cc7e0a35-4554-4864-9437-b6f651c979c4'),
('8a857683-b5c2-438e-a5b3-3708803640a0', '2019-03-02 03:01:42', 'active', '8b7e3eb2-ba9a-4505-b9aa-c65c9c718e5d', '1f58d443-55f7-44ad-afc8-576d55ea2aea'),
('42e2778d-d491-450f-968c-f80fb877db2a', '2019-03-02 03:02:45', 'active', 'da6316b8-767b-4800-bf3b-e75d5c162826', 'c7808eba-2902-4ad2-a470-1e1668ce567f'),
('4b988c78-159f-429b-be74-ccf5e036abd8', '2019-03-02 03:01:48', 'active', 'da6316b8-767b-4800-bf3b-e75d5c162826', 'cc7e0a35-4554-4864-9437-b6f651c979c4'),
('263a47a9-021c-4f17-b20e-52452baa8783', '2019-03-02 03:01:52', 'active', '60858d1f-8c4f-4319-b44a-82f2cf95a417', '1f58d443-55f7-44ad-afc8-576d55ea2aea'),
('4447f94c-c280-40e6-9954-62d5ecd7b9b6', '2019-03-02 03:01:56', 'active', '8b4ad06e-0187-4082-9861-7095542ccf5c', '1f58d443-55f7-44ad-afc8-576d55ea2aea'),
('a1e5ec3b-941d-4088-b325-9415e45fec80', '2019-03-02 03:02:02', 'active', 'da6316b8-767b-4800-bf3b-e75d5c162826', '65611e0b-c6ac-4e7f-8ac9-97ef08de1dbf'),
('7796b262-89f8-4809-9da5-0823aee89f0d', '2019-03-02 03:02:02', 'active', 'da6316b8-767b-4800-bf3b-e75d5c162826', 'cc7e0a35-4554-4864-9437-b6f651c979c4'),
('040a5860-899c-4e8a-8522-7409551e441d', '2019-03-02 03:03:06', 'active', '60858d1f-8c4f-4319-b44a-82f2cf95a417', 'c7808eba-2902-4ad2-a470-1e1668ce567f'),
('029fc5a3-c8c6-48e0-8716-5226bda0a38c', '2019-03-02 03:04:32', 'active', '8b7e3eb2-ba9a-4505-b9aa-c65c9c718e5d', '7836c574-628a-4eae-801d-e88a54a56e11'),
('ea446541-9c2f-4146-8af3-2c20f771955b', '2019-03-02 05:13:50', 'active', '8b7e3eb2-ba9a-4505-b9aa-c65c9c718e5d', '739e747e-aafe-448d-85cb-24159e21c1af'),
('5e3dfb00-4a2c-40e0-9964-c80fe7a9d290', '2019-03-02 05:27:26', 'active', '0e980b0c-3e69-40d2-a046-32a2927b4016', 'ba98bf48-35be-4963-9cf3-f57d1c523be3'),
('6a671e45-44c3-4416-ae3a-a7097add3152', '2019-03-02 05:28:04', 'active', '0e980b0c-3e69-40d2-a046-32a2927b4016', 'efba9e7c-10c5-4602-b8d8-df128e2d6446'),
('8124d8f6-bade-4f1c-aef3-fbe5316149db', '2019-03-02 05:37:58', 'active', '0e980b0c-3e69-40d2-a046-32a2927b4016', 'c4ae4987-e432-4a57-b054-45128febeafc'),
('07266d06-3997-4fc0-a132-b1641dcd45f1', '2019-03-02 06:03:22', 'active', '5ea48741-7b0a-467a-8c4e-7f617454f8e3', 'b2a15c13-5f99-469d-9b3a-720badb2d977'),
('638fc827-3da5-4052-b7ce-0647708ba58f', '2019-03-02 06:03:25', 'active', '5ea48741-7b0a-467a-8c4e-7f617454f8e3', 'b2a15c13-5f99-469d-9b3a-720badb2d977'),
('23b39454-5d14-435a-b816-7079c775ef35', '2019-03-02 06:03:52', 'active', '5ea48741-7b0a-467a-8c4e-7f617454f8e3', 'b2a15c13-5f99-469d-9b3a-720badb2d977'),
('80ccd79c-e5d5-42f7-9910-e4d6a1ed0fc2', '2019-03-02 06:08:24', 'active', '5ea48741-7b0a-467a-8c4e-7f617454f8e3', 'd09c0c26-b68a-45f8-a35a-01efd8dbb63d'),
('caa8be77-9d4f-4c0d-8109-1191143763f0', '2019-03-02 06:08:55', 'active', '5ea48741-7b0a-467a-8c4e-7f617454f8e3', 'a0713af5-4f57-42bc-b887-09a918164cfe'),
('5a5975fa-4fc4-4dea-baaa-ea6cc2fa7a68', '2019-03-02 06:15:14', 'active', '5ea48741-7b0a-467a-8c4e-7f617454f8e3', '3ca1134a-a314-44fb-810f-017225eff5df'),
('e5fcaf25-3e04-470e-bd09-626a18893676', '2019-03-02 06:15:44', 'active', '0e980b0c-3e69-40d2-a046-32a2927b4016', '3ca1134a-a314-44fb-810f-017225eff5df'),
('e8fa9125-e47c-4c8c-9ac6-7931d74e7242', '2019-03-02 06:16:20', 'active', '5ea48741-7b0a-467a-8c4e-7f617454f8e3', '4239ee43-ae45-4f4a-8eb8-43bf6c1650cb'),
('916fda11-c342-4919-8497-c02cf81ff7db', '2019-03-02 06:16:36', 'active', '0e980b0c-3e69-40d2-a046-32a2927b4016', '4239ee43-ae45-4f4a-8eb8-43bf6c1650cb'),
('02fd8b75-286c-44c6-a584-043b4c86dff3', '2019-03-02 06:30:48', 'active', '5ea48741-7b0a-467a-8c4e-7f617454f8e3', '936706e5-7511-4211-bf54-7cf42a718445'),
('02c2edd8-bff2-4ee7-b9b8-5872617a0880', '2019-03-02 06:31:08', 'active', '48778ac6-51ef-4798-b568-760acd21b499', '936706e5-7511-4211-bf54-7cf42a718445'),
('6ebe1291-288d-43e3-a79e-f72203898acf', '2019-03-09 03:06:29', 'active', '39a1d09f-3fc6-425e-a458-127dbb78bfc9', '7ea9c68d-a6ec-4d57-98c8-5ea690b9da19'),
('3c37c64a-a27c-4a40-b105-4f32a080ec4d', '2019-03-09 03:07:39', 'active', '39a1d09f-3fc6-425e-a458-127dbb78bfc9', '6b1c9470-f40a-47e3-8533-4ba8c8619dc9'),
('7eaec25a-bedf-418a-8229-7fa3866fa4ec', '2019-03-09 03:08:42', 'active', '39a1d09f-3fc6-425e-a458-127dbb78bfc9', '3ca1134a-a314-44fb-810f-017225eff5df'),
('3e01312d-23c8-45dd-8c96-ec9ee098fdd3', '2019-03-09 03:14:10', 'active', '39a1d09f-3fc6-425e-a458-127dbb78bfc9', 'c7806d65-92f3-497b-bc4d-034266295f9a'),
('32ba4f03-d3bc-408e-b157-9776ce894c7e', '2019-03-09 03:14:18', 'active', 'ec744fc9-be5c-4a12-87d6-52e3a5f8a8f4', 'c7806d65-92f3-497b-bc4d-034266295f9a'),
('a06ea4b1-4f11-4ec9-bed1-6c3e575e477d', '2019-03-09 03:26:36', 'active', '39a1d09f-3fc6-425e-a458-127dbb78bfc9', 'a0713af5-4f57-42bc-b887-09a918164cfe'),
('c5e948c1-8438-4316-9302-782595b55dab', '2019-03-09 03:26:50', 'active', 'ec744fc9-be5c-4a12-87d6-52e3a5f8a8f4', 'a0713af5-4f57-42bc-b887-09a918164cfe'),
('14bb06af-bebb-4fc1-87ea-bc91c7ddb8fb', '2019-03-09 03:27:10', 'active', '5b8e2777-99ff-47fb-8dd0-cae0bb0fd4c3', 'a0713af5-4f57-42bc-b887-09a918164cfe'),
('a6f1d7fe-d305-4535-b7ca-8d9d7d019078', '2019-03-09 03:28:18', 'active', '5b8e2777-99ff-47fb-8dd0-cae0bb0fd4c3', 'b6f1a045-192d-43ef-a845-06c2436dfbdd'),
('fad12426-95bd-432b-98b9-71dfb4dbb356', '2019-03-09 03:28:44', 'active', '5b8e2777-99ff-47fb-8dd0-cae0bb0fd4c3', '3ca1134a-a314-44fb-810f-017225eff5df'),
('ad06e33b-0c13-47c5-a1d4-8cee69fbce42', '2019-03-09 04:01:47', 'active', '39a1d09f-3fc6-425e-a458-127dbb78bfc9', '7ea9c68d-a6ec-4d57-98c8-5ea690b9da19'),
('ed64d3a2-223b-4cb0-9f15-299f0c277271', '2019-03-09 04:02:20', 'active', '5b8e2777-99ff-47fb-8dd0-cae0bb0fd4c3', '7ea9c68d-a6ec-4d57-98c8-5ea690b9da19');

-- --------------------------------------------------------

--
-- Table structure for table `Garage`
--

CREATE TABLE IF NOT EXISTS `Garage` (
  `garageId` varchar(255) NOT NULL,
  `background` varchar(255) DEFAULT NULL,
  `creditCardNumber` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `garageType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`garageId`),
  KEY `FK_9ao2vct11e3a3upgndc2ar724` (`owner`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Garage`
--

INSERT INTO `Garage` (`garageId`, `background`, `creditCardNumber`, `location`, `logo`, `name`, `status`, `website`, `owner`, `garageType`) VALUES
('062a570c-aa2e-41f6-a985-926bcbf24926', 'garage service', '10203040', 'nyarugunga', NULL, 'garage fma', 'approved', 'www.fma@yahoo.com', '4daaf64d-fe71-448e-ba2a-b0b082b32857', 'Expert'),
('48778ac6-51ef-4798-b568-760acd21b499', 'repairing cars', '19910', 'nyarugunga', NULL, 'metropole', 'approved', 'www.metro.rwa', '61146423-6589-421d-ae04-70af4453d4e3', 'Expert'),
('5ea48741-7b0a-467a-8c4e-7f617454f8e3', 'CAR REPAIR', '101111111', 'kigali,gasabo,kimironko, nyagatovu', NULL, 'Super Car', 'approved', 'www.supercar.com', 'd06a8e13-e52b-465c-95aa-e2b94cc5926a', 'Expert'),
('50cc32c1-551c-4eef-83db-7ea83cf2fd45', 'Car Repair', '108541002', 'Muhima', NULL, 'ATECAR', 'approved', 'www.atecar.rw', '9dcd9485-e7b7-455f-8a9a-8c209fb89c00', 'garage'),
('0e980b0c-3e69-40d2-a046-32a2927b4016', 'ddkdkdkdk', 'kddkkdk', 'kigali,nyarugenge,muhima', NULL, 'Garage a', 'approved', 'kdkkddkkd', 'f7b92cb9-3174-487b-b9ac-d9ae01e16a61', 'Expert'),
('39a1d09f-3fc6-425e-a458-127dbb78bfc9', 'mecanics', '201', 'kimironko', NULL, 'the best garage', 'approved', 'www. the best', '36dd4d01-e406-4eab-87ed-ff0ddeccf56d', 'Expert'),
('ec744fc9-be5c-4a12-87d6-52e3a5f8a8f4', 'test', '12333', 'kigali,nyarugenge', NULL, 'Garage Admin', 'approved', 'www.garage4.com', 'e46d9009-4d32-40d6-891a-2305d6684db4', 'Expert'),
('5b8e2777-99ff-47fb-8dd0-cae0bb0fd4c3', 'Car repair and expertise Services', '111111111111', 'Kigali,kimironko,Nyagatovu', NULL, 'PRINCOJ', 'approved', 'www.princo.rw', 'ae11d739-30c0-43fe-b30b-138bd7dd6710', 'Expert'),
('285125c3-b326-429e-ac10-814fef6b66bb', 'car repair', '22222222222', 'Nyagatovu', NULL, 'grand Ltd', 'approved', 'www.grand.rw', 'a5e82cf4-9d4c-4573-99c6-d5cfc7e0ad22', 'garage'),
('8b61d3f7-b312-420b-b8b5-d5d721a540f7', 'repairing ', '2015', 'kabuga', NULL, 'zirakorwa', 'approved', 'www.zirakorwa', '799d6bf6-8d3f-4fdb-94ec-b7e0b1628a0e', 'garage');

-- --------------------------------------------------------

--
-- Table structure for table `GarageOwner`
--

CREATE TABLE IF NOT EXISTS `GarageOwner` (
  `ownerId` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ownerId`),
  KEY `FK_7t4ks5u7q5gxkk9x8nvbf65xd` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `GarageOwner`
--

INSERT INTO `GarageOwner` (`ownerId`, `name`, `phoneNumber`, `userId`) VALUES
('36dd4d01-e406-4eab-87ed-ff0ddeccf56d', 'gikundiro', '+250 728 201 286', 'ddbf9895-81d1-4579-baa4-6fd7ec301d6a'),
('4daaf64d-fe71-448e-ba2a-b0b082b32857', 'karambizi innocent', '+250 788 301 999', 'd155f399-d4d7-4aaa-8022-b1a26666a9ea'),
('61146423-6589-421d-ae04-70af4453d4e3', 'uwizera denise', '+250 785 259 109', '6b985100-3698-4ec2-a58a-f19143248cd7'),
('d06a8e13-e52b-465c-95aa-e2b94cc5926a', 'Prince ', '+250 786 546 056', '71f33495-c124-4755-bbbb-49dc16722417'),
('9dcd9485-e7b7-455f-8a9a-8c209fb89c00', 'Nsengiyumva', '+250 788 210 354', '5bf70972-d83c-4b37-b1b7-92e1349749e4'),
('f7b92cb9-3174-487b-b9ac-d9ae01e16a61', 'Gatsinzi Erneste', '+250 725 633 333', '3b8856cd-e7a8-41aa-b617-a3a0dffa986a'),
('e46d9009-4d32-40d6-891a-2305d6684db4', 'irakoze Eric', '+250 722 320 568', '74aa6cb3-1211-43ac-85aa-a40050fae028'),
('ae11d739-30c0-43fe-b30b-138bd7dd6710', 'Prince ', '+250 726 091 629', '05457cb3-36a0-48f1-b859-265a5ac8bbd3'),
('a5e82cf4-9d4c-4573-99c6-d5cfc7e0ad22', 'Prince jean', '+250 738 485 419', '47779aad-52df-4f41-a103-f8727f78124d'),
('799d6bf6-8d3f-4fdb-94ec-b7e0b1628a0e', 'isaac', '+250 788 201 286', '6f3d32e3-8248-49b9-b641-2fe0ac17361d');

-- --------------------------------------------------------

--
-- Table structure for table `InsuranceCompany`
--

CREATE TABLE IF NOT EXISTS `InsuranceCompany` (
  `uuid` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `terms` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FK_p5flc00hgras1f7a54kmddcm7` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `InsuranceCompany`
--

INSERT INTO `InsuranceCompany` (`uuid`, `description`, `name`, `phoneNumber`, `terms`, `userId`) VALUES
('ac4557cb-3528-4c83-8db1-b3fd41055b87', NULL, 'yvonne', '+250 788 393 798', NULL, 'c7174533-3f49-49d6-9652-e9bd0e0cccb4'),
('bfdfe728-d990-4b62-b9e8-6cfa4ce732dc', NULL, 'MUKESHIMANA', '+250 788 393 798', NULL, '18ddea53-b447-4a54-ba4d-9ea8137d5def'),
('a6ff0b4a-c4aa-48c7-85be-ea0ef500b721', NULL, 'MUKESHIMANA', '+250 788 393 798', NULL, '2af7dbcc-d0b8-42ea-87ed-cdb87a7864c1'),
('936706e5-7511-4211-bf54-7cf42a718445', NULL, 'baho ltd', '+250 788 201 286', NULL, '411cca39-912e-458a-bf3b-78fb3c7bd6df'),
('1446be3a-a699-4472-8c02-98e09f51e303', NULL, 'MUKESHIMANA', '+250 788 393 798', NULL, '522c9af3-92c8-4418-95bd-4516172fab07'),
('a0713af5-4f57-42bc-b887-09a918164cfe', NULL, 'Munyaneza', '+250 725 123 520', NULL, 'af5a8712-8119-4206-8d9f-51c34cc24f70'),
('3ca1134a-a314-44fb-810f-017225eff5df', NULL, 'THE KING ', '+250 730 062 081', NULL, '97606a79-6235-4665-b998-ff3276ce53dc'),
('c4ae4987-e432-4a57-b054-45128febeafc', NULL, 'sonarwa', '+250 722 320 568', NULL, '7399b57d-266c-4788-929f-a8297f673497'),
('5ee53b43-ee32-4950-a1ab-d2a26d29529c', NULL, 'yvonne', '+250 788 393 798', NULL, '722b02fe-366c-4345-9715-8045fe026ceb'),
('5440175b-3f0a-4ab2-aecd-3c79b30d94d2', NULL, 'UWINEZA', '+250 788 393 798', NULL, '51be00a5-050c-45d4-8718-5776412f1132'),
('37d0097b-4f80-4251-b0df-5244788d19f3', NULL, 'MUKAMANA', '+250 788 393 798', NULL, '762d2dde-c310-4eee-be24-626a6a6c8b40'),
('7ea9c68d-a6ec-4d57-98c8-5ea690b9da19', NULL, 'baho', '+250 788 201 286', NULL, 'a4ffbe8e-7c6d-4237-bc8b-ca73cd281509');

-- --------------------------------------------------------

--
-- Table structure for table `PayForBid`
--

CREATE TABLE IF NOT EXISTS `PayForBid` (
  `uuid` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `biddingId` varchar(255) DEFAULT NULL,
  `garageId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FK_t9mpm5g99jlbausqwyfiije7q` (`biddingId`),
  KEY `FK_6x5ndgqfskqmxr7dblt0xj8t0` (`garageId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Payment`
--

CREATE TABLE IF NOT EXISTS `Payment` (
  `uuid` varchar(255) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `creditCard` varchar(255) DEFAULT NULL,
  `completedCar` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FK_ayawq0436vik28lbc1hsuy22x` (`completedCar`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Police`
--

CREATE TABLE IF NOT EXISTS `Police` (
  `id` varchar(255) NOT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c4b9nxdwvnrh36ngwks4tw0yd` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `PoliceReport`
--

CREATE TABLE IF NOT EXISTS `PoliceReport` (
  `uuid` varchar(255) NOT NULL,
  `additionalDocument` varchar(255) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `description` text,
  `location` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `iyagonzeVehicle_vehicleId` varchar(255) DEFAULT NULL,
  `iyagonzweVehicle_vehicleId` varchar(255) DEFAULT NULL,
  `reportedBy` varchar(255) DEFAULT NULL,
  `thirdParty` varchar(255) DEFAULT NULL,
  `whoisResponsibeForThis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FK_d9shcoehmqbmnwsxif5bf4bar` (`iyagonzeVehicle_vehicleId`),
  KEY `FK_dacsujooxuv1iiep4impi5v1j` (`iyagonzweVehicle_vehicleId`),
  KEY `FK_73f4nvcxehkly8ll5e1qiivj8` (`reportedBy`),
  KEY `FK_255g7vkj3o6bhjn5i82yg75ia` (`thirdParty`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Quotation`
--

CREATE TABLE IF NOT EXISTS `Quotation` (
  `uuid` varchar(255) NOT NULL,
  `jobDescription` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT '0',
  `biddingId` varchar(255) DEFAULT NULL,
  `brokenCarPart` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FK_piflb77cl5feo07f7tucnq5ef` (`biddingId`),
  KEY `FK_j8l8edsjjx92uvs2qmvhtlak1` (`brokenCarPart`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Quotation`
--

INSERT INTO `Quotation` (`uuid`, `jobDescription`, `price`, `quantity`, `biddingId`, `brokenCarPart`) VALUES
('552edee3-fbe7-4620-8c83-f0304f98109a', NULL, 15000, 0, 'd4659f06-73fa-4e80-989e-42be5d991733', '506cd759-f48c-47cb-beeb-7c3851f4ea1e'),
('0d984a00-2fcb-427a-8ca7-d06c61cc011b', NULL, 10000, 0, 'd4659f06-73fa-4e80-989e-42be5d991733', 'a6348312-55b1-4c85-9f46-f253867f52ca'),
('7191f4bc-5ca4-46c2-99e7-ae6507b104a6', NULL, 4500, 0, 'ccbedf16-00b2-4f1f-8ee5-47a44430a9a4', 'ac356387-ddd5-4e06-9e12-960bae38db0c'),
('c6fc0244-050f-40fc-8827-885a6524d492', NULL, 2000, 0, 'ccbedf16-00b2-4f1f-8ee5-47a44430a9a4', '0f9f1e2f-d9f2-4be2-8bc8-d7cf503968b6'),
('85b1ae65-e891-4581-a8d7-89333421e4cb', NULL, 80000, 0, 'ccbedf16-00b2-4f1f-8ee5-47a44430a9a4', '15159618-0a7b-4c47-afec-4672f542e85f'),
('2dd3e2f1-dace-4be1-be87-be17d4c9e5bb', NULL, 4500, 0, 'ccbedf16-00b2-4f1f-8ee5-47a44430a9a4', '0b241a67-2f9e-4daf-bb22-0e8d311910ef'),
('62606716-38c1-423a-a721-379d05b67c1b', NULL, 7800, 0, 'ccbedf16-00b2-4f1f-8ee5-47a44430a9a4', '1540e410-615d-41fa-8e2a-c6e2adfa3044'),
('53ae1af0-69a2-4f1e-9cb7-cf4657dbd4d9', NULL, 12000, 0, '4c692baa-01b4-40a3-beaa-29130b9914ec', 'ac356387-ddd5-4e06-9e12-960bae38db0c'),
('17373688-3014-4a03-9089-5194dfd73dd2', NULL, 700, 0, '4c692baa-01b4-40a3-beaa-29130b9914ec', '0f9f1e2f-d9f2-4be2-8bc8-d7cf503968b6'),
('3753862d-4867-422f-9ccf-31120bc9c5af', NULL, 1000, 0, '4c692baa-01b4-40a3-beaa-29130b9914ec', '15159618-0a7b-4c47-afec-4672f542e85f'),
('380388dc-a593-47f8-a143-c17c43f25704', NULL, 28000, 0, '4c692baa-01b4-40a3-beaa-29130b9914ec', '0b241a67-2f9e-4daf-bb22-0e8d311910ef'),
('d9b86fb7-8ab2-4d82-947d-623cbba1933c', NULL, 125000, 0, '4c692baa-01b4-40a3-beaa-29130b9914ec', '1540e410-615d-41fa-8e2a-c6e2adfa3044'),
('6813c96b-c80f-4cc1-918e-1faa1ac92c95', NULL, 5000, 0, '606273cd-9d47-4f38-a164-8aa55adee3a2', 'ac356387-ddd5-4e06-9e12-960bae38db0c'),
('d1df8db5-4355-49bb-ad74-e92327f04dc4', NULL, 30000, 0, '606273cd-9d47-4f38-a164-8aa55adee3a2', '0f9f1e2f-d9f2-4be2-8bc8-d7cf503968b6'),
('ebb4a3fd-8a05-4a17-b41a-ade54730ccd0', NULL, 2000, 0, '606273cd-9d47-4f38-a164-8aa55adee3a2', '15159618-0a7b-4c47-afec-4672f542e85f'),
('eb6e0a59-2f50-48b9-ae3c-494031f2d38a', NULL, 5200, 0, '606273cd-9d47-4f38-a164-8aa55adee3a2', '0b241a67-2f9e-4daf-bb22-0e8d311910ef'),
('9312cbd2-fcfd-4944-9c72-ef79173f8047', NULL, 8000, 0, '606273cd-9d47-4f38-a164-8aa55adee3a2', '1540e410-615d-41fa-8e2a-c6e2adfa3044'),
('bb2326cc-9b8b-4f81-a722-ad428fa06994', NULL, 50000, 0, '441fe734-d24e-4c9d-a4cf-b79ecacc9861', 'd772053d-8695-41eb-a72b-260bfaaa10f0'),
('8d0fdcc1-eb63-402a-bb21-8afe8ea09ef8', NULL, 20000, 0, '441fe734-d24e-4c9d-a4cf-b79ecacc9861', '7a3ea9c4-784e-4b70-8f87-82b196da36c3'),
('9f191e0b-fad5-41eb-9d20-0c038e2d5866', NULL, 60000, 0, '441fe734-d24e-4c9d-a4cf-b79ecacc9861', 'f4e8e95e-d775-4b6b-b282-52a85118227e');

-- --------------------------------------------------------

--
-- Table structure for table `ThirdParty`
--

CREATE TABLE IF NOT EXISTS `ThirdParty` (
  `uuid` varchar(255) NOT NULL,
  `createAt` datetime DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nationalId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `userId` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`userId`, `password`, `status`, `userName`, `userType`) VALUES
('7b10e799-ef36-4ea2-bd1c-89ecea1eba09', '202cb962ac59075b964b07152d234b70', 'active', 'jprince4066@gmail.com', 'client'),
('ecc5685a-29f1-43a2-a625-44e6e5b882dd', 'f0c14bd1ed2c64b1f0347363528a091e', 'active', 'nestabianca8@gmail.com', 'client'),
('bf51f6c4-3b36-4b87-9613-572b45776c6d', '202cb962ac59075b964b07152d234b70', 'active', 'nesyl05@yahoo.fr', 'client'),
('762d2dde-c310-4eee-be24-626a6a6c8b40', '1be3b5b92be2a3b1ceec8fcfda80e2d9', 'active', 'mukamanayvonne@gmail.com', 'insurance'),
('51be00a5-050c-45d4-8718-5776412f1132', 'f7edafb68edfc0ae039c45568800573c', 'active', 'nestayvonne@gmail.com', 'insurance'),
('722b02fe-366c-4345-9715-8045fe026ceb', 'f7edafb68edfc0ae039c45568800573c', 'active', 'uwinezayvonne2@gmail.com', 'insurance'),
('c7174533-3f49-49d6-9652-e9bd0e0cccb4', '201b72a58396e4eaa8c8e9cdd059ca4d', 'active', 'yvonnemukeshimana@gmail.com', 'insurance'),
('18ddea53-b447-4a54-ba4d-9ea8137d5def', '7967d2245d7ea872215c6de59978865f', 'active', 'nestayvonne@gmail.com', 'insurance'),
('2af7dbcc-d0b8-42ea-87ed-cdb87a7864c1', '7967d2245d7ea872215c6de59978865f', 'active', 'nestabianca@gmail.com', 'insurance'),
('71f33495-c124-4755-bbbb-49dc16722417', '202cb962ac59075b964b07152d234b70', 'active', 'nizeyimanajeanprince@gmail.com', 'Expert'),
('5bf70972-d83c-4b37-b1b7-92e1349749e4', '3824795e4e1fbf0f72f1cf99ee90d861', 'active', 'nesyl05@gmail.com', 'garage'),
('411cca39-912e-458a-bf3b-78fb3c7bd6df', '0c42bef0d9353c68111b18724c72b531', 'active', 'isaacuwize@gmail.com', 'insurance'),
('522c9af3-92c8-4418-95bd-4516172fab07', '7967d2245d7ea872215c6de59978865f', 'active', 'nestabianca8@gmail.com', 'insurance'),
('af5a8712-8119-4206-8d9f-51c34cc24f70', '202cb962ac59075b964b07152d234b70', 'active', 'munyak@gmail.com', 'insurance'),
('97606a79-6235-4665-b998-ff3276ce53dc', '202cb962ac59075b964b07152d234b70', 'active', 'j.princo4066@gmail.info', 'insurance'),
('5b601ead-83e2-4598-8242-862e4e556213', '202cb962ac59075b964b07152d234b70', 'active', 'iyaturemyeclaude@gmail.com', 'client'),
('7399b57d-266c-4788-929f-a8297f673497', '202cb962ac59075b964b07152d234b70', 'active', 'sonarwa@gmail.com', 'insurance'),
('8c42f4f9-3cdd-11e9-9745-00155d088914', '3824795e4e1fbf0f72f1cf99ee90d861', 'active', 'admin@gmail.com', 'admin'),
('3b8856cd-e7a8-41aa-b617-a3a0dffa986a', '3824795e4e1fbf0f72f1cf99ee90d861', 'active', 'garagea@gmail.com', 'Expert'),
('6e21fedc-e001-4ecd-b71a-0511dc2393e7', 'e9a947b34ce86fd093b3b5df2cf749a4', 'active', 'isaacuwize@yahoo.com', 'client'),
('072dcb59-51b8-4900-9217-db686e3a38b8', '408c0a71e9d2ba257a128ae2f579872e', 'active', 'nestabianca8@gmail.com', 'client'),
('6b985100-3698-4ec2-a58a-f19143248cd7', '3824795e4e1fbf0f72f1cf99ee90d861', 'active', 'uwizeradenise@gmail.com', 'Expert'),
('2e725cf4-4619-4235-a1a7-a3fa83877c49', '202cb962ac59075b964b07152d234b70', 'active', 'kigali@gmail.com', 'client'),
('b37e1d1e-804b-470d-85f1-e86cad4a86cf', '97014654b84fb055f7accc18932a3f62', 'active', 'gikundiro@gmail.com', 'client'),
('93ddfd13-f8e3-420f-8101-a822ada2029d', '202cb962ac59075b964b07152d234b70', 'active', 'denuwize@gmail.com', 'client'),
('fb5d10a1-61fb-4dd7-925a-bf82c29685a4', '3824795e4e1fbf0f72f1cf99ee90d861', 'active', 'kigali@gmail.com', 'insurance'),
('d155f399-d4d7-4aaa-8022-b1a26666a9ea', '3824795e4e1fbf0f72f1cf99ee90d861', 'active', 'krmbzinnocent3@gmail.com', 'Expert'),
('fdc145a6-5c02-4822-a74d-eb2fcb2e61d8', 'a154a04b7666b25af0a86e53beb17dcf', 'active', 'krmbzinnocent3@gmail.com', 'client'),
('ddbf9895-81d1-4579-baa4-6fd7ec301d6a', '202cb962ac59075b964b07152d234b70', 'active', 'uwizeyimana@gmail.com', 'Expert'),
('a4ffbe8e-7c6d-4237-bc8b-ca73cd281509', '729db1d6dc6e6be589df0232c793e7f1', 'active', 'baho@gmail.com', 'insurance'),
('74aa6cb3-1211-43ac-85aa-a40050fae028', '3824795e4e1fbf0f72f1cf99ee90d861', 'active', 'garageadmin', 'Expert'),
('05457cb3-36a0-48f1-b859-265a5ac8bbd3', '3824795e4e1fbf0f72f1cf99ee90d861', 'active', 'nizeyimanajeanprince1@gmail.com', 'Expert'),
('47779aad-52df-4f41-a103-f8727f78124d', '3824795e4e1fbf0f72f1cf99ee90d861', 'active', 'nizeyimanajeanprince2@gmail.com', 'garage'),
('6f3d32e3-8248-49b9-b641-2fe0ac17361d', '3824795e4e1fbf0f72f1cf99ee90d861', 'active', 'zirakorwa@gmail.com', 'garage');

-- --------------------------------------------------------

--
-- Table structure for table `Vehicle`
--

CREATE TABLE IF NOT EXISTS `Vehicle` (
  `vehicleId` varchar(255) NOT NULL,
  `chasisNum` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `plateNum` varchar(255) DEFAULT NULL,
  `policyNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vehicleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Vehicle`
--

INSERT INTO `Vehicle` (`vehicleId`, `chasisNum`, `name`, `plateNum`, `policyNumber`) VALUES
('e7e805c8-dae3-4a78-a34e-68c52d4a3e7f', NULL, 'RAVA4', 'RAC456J', '10258742'),
('13293ac8-3bdd-43e9-9707-9db29927054b', NULL, 'Land over', 'RAE111Z', '02045T'),
('45791b1d-d8ce-4ee8-acb7-79509133616d', '', '', '', ''),
('7e7010ac-d9ce-42b9-bd39-95e9922a4049', NULL, 'dkdkdk', 'Rad 329', 'kdkdk');

-- --------------------------------------------------------

--
-- Table structure for table `VehicleDetail`
--

CREATE TABLE IF NOT EXISTS `VehicleDetail` (
  `uuid` varchar(255) NOT NULL,
  `UpdatedAt` datetime DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `mastakeOwner` varchar(255) DEFAULT NULL,
  `readOrUnread` varchar(255) DEFAULT NULL,
  `statementOfVehicle` text,
  `status` varchar(255) DEFAULT NULL,
  `vehicleaEnsuranceEndingDate` date DEFAULT NULL,
  `vehicleaEnsuranceStartDate` date DEFAULT NULL,
  `vehiclebEnsuranceEndingDate` date DEFAULT NULL,
  `vehiclebEnsuranceStartDate` date DEFAULT NULL,
  `whatHappen` text,
  `clientId` varchar(255) DEFAULT NULL,
  `driverId` varchar(255) DEFAULT NULL,
  `expectiseGarageId` varchar(255) DEFAULT NULL,
  `insuranceId` varchar(255) DEFAULT NULL,
  `insuranceOfVehicleb` varchar(255) DEFAULT NULL,
  `vehicleId` varchar(255) DEFAULT NULL,
  `vehiclebId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FK_gnc00ydtkt7oys18nxwkivaor` (`clientId`),
  KEY `FK_6xhyxwp1ehvtkwc4t7br0u4q7` (`driverId`),
  KEY `FK_klmnr2k1scufnsks8k6u3vv52` (`expectiseGarageId`),
  KEY `FK_lvkxel97a9lcw4pyve1ek32eu` (`insuranceId`),
  KEY `FK_py6vubllhbgjecn6r1xa5ee4n` (`insuranceOfVehicleb`),
  KEY `FK_mqt5kbia0c69y6btu54gbf5k6` (`vehicleId`),
  KEY `FK_2saxug7g1h3ped382gc02f8sj` (`vehiclebId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `VehicleDetail`
--

INSERT INTO `VehicleDetail` (`uuid`, `UpdatedAt`, `createdAt`, `location`, `mastakeOwner`, `readOrUnread`, `statementOfVehicle`, `status`, `vehicleaEnsuranceEndingDate`, `vehicleaEnsuranceStartDate`, `vehiclebEnsuranceEndingDate`, `vehiclebEnsuranceStartDate`, `whatHappen`, `clientId`, `driverId`, `expectiseGarageId`, `insuranceId`, `insuranceOfVehicleb`, `vehicleId`, `vehiclebId`) VALUES
('3ffb4a5b-8b83-4ea1-b541-50e17f83b8ff', '2019-03-09 07:11:28', '2019-03-09 07:11:28', 'Kanombe', 'me', 'read', NULL, 'proccessed', NULL, '2019-08-05', NULL, NULL, 'Check the problems&nbsp;', '91c528ee-670d-4500-9345-b623f75251d8', NULL, '14bb06af-bebb-4fc1-87ea-bc91c7ddb8fb', 'a0713af5-4f57-42bc-b887-09a918164cfe', NULL, 'e7e805c8-dae3-4a78-a34e-68c52d4a3e7f', '45791b1d-d8ce-4ee8-acb7-79509133616d'),
('ebac1eb2-ed6f-4471-bbd8-a4885c2ae6a9', '2019-03-09 06:51:51', '2019-03-09 06:51:51', 'kigali,nyarugenge', 'Her', 'read', NULL, 'completed', NULL, '2019-03-31', NULL, NULL, '', '4478b297-fcea-468c-8286-6ea55adb51e8', NULL, '8124d8f6-bade-4f1c-aef3-fbe5316149db', 'c4ae4987-e432-4a57-b054-45128febeafc', NULL, '7e7010ac-d9ce-42b9-bd39-95e9922a4049', '45791b1d-d8ce-4ee8-acb7-79509133616d'),
('75d4e9bc-b0d9-47b1-b7b1-814b900a27d5', '2019-03-09 07:11:02', '2019-03-09 07:11:02', 'Kigali, Gasabo, Kimironko', 'Her', 'read', NULL, 'completed', NULL, '2019-03-23', NULL, NULL, 'Alcohol effect', '7dc0fced-beb2-4d8b-8533-f0a878bd5473', NULL, 'fad12426-95bd-432b-98b9-71dfb4dbb356', '3ca1134a-a314-44fb-810f-017225eff5df', NULL, '13293ac8-3bdd-43e9-9707-9db29927054b', '45791b1d-d8ce-4ee8-acb7-79509133616d'),
('4b18d1c4-a465-487f-9471-ce085e6cb69e', '2019-03-09 06:44:31', '2019-03-09 06:44:31', 'kigali,nyarugenge', 'me', 'read', NULL, 'proccessed', NULL, '2019-03-28', NULL, NULL, 'himself', '4478b297-fcea-468c-8286-6ea55adb51e8', NULL, '7eaec25a-bedf-418a-8229-7fa3866fa4ec', '3ca1134a-a314-44fb-810f-017225eff5df', NULL, '7e7010ac-d9ce-42b9-bd39-95e9922a4049', '45791b1d-d8ce-4ee8-acb7-79509133616d');

-- --------------------------------------------------------

--
-- Table structure for table `VehicleImage`
--

CREATE TABLE IF NOT EXISTS `VehicleImage` (
  `vimageId` varchar(255) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `vehicleDetailId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vimageId`),
  KEY `FK_88yjujohqyathdae6v5csp4ib` (`vehicleDetailId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `VehicleImage`
--

INSERT INTO `VehicleImage` (`vimageId`, `createdAt`, `image`, `vehicleDetailId`) VALUES
('59b8ed1e-51d1-4741-8134-371ffaf003ae', '2019-03-09 07:13:06', 'b2a9d555-d343-49a0-88d9-0bd232633ec2.jpg', '3ffb4a5b-8b83-4ea1-b541-50e17f83b8ff'),
('bd341c6b-052a-4e81-b6bf-4732955095e3', '2019-03-09 07:12:46', 'e72372a3-9f59-48b6-b7e8-dd9227c31999.jpg', '75d4e9bc-b0d9-47b1-b7b1-814b900a27d5'),
('9b828272-89d0-4ccb-b2bd-dd35109e7ae5', '2019-03-09 06:52:53', '80fe53b9-94d6-4ec7-86ca-0388aa648cdf.png', 'ebac1eb2-ed6f-4471-bbd8-a4885c2ae6a9'),
('634c3ecf-b108-4e6f-99e7-a421923eca6b', '2019-03-09 06:46:04', '609a8804-5102-44af-8831-c2d0a091b59f.png', '4b18d1c4-a465-487f-9471-ce085e6cb69e');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
