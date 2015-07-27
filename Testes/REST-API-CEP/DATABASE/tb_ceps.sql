-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Máquina: localhost
-- Data de Criação: 23-Jul-2015 às 18:43
-- Versão do servidor: 5.1.73-cll
-- versão do PHP: 5.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `dzdesign_zup`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_ceps`
--

CREATE TABLE IF NOT EXISTS `tb_ceps` (
  `cep` varchar(8) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `bairro` varchar(255) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL,
  UNIQUE KEY `cep` (`cep`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tb_ceps`
--

INSERT INTO `tb_ceps` (`cep`, `endereco`, `bairro`, `cidade`, `estado`, `data`, `status`) VALUES
('15025050', 'Rua Tiradentes', 'Parque Industrial', 'São José do Rio Preto', 'SP', '2015-07-23 21:31:56', 1),
('15025055', 'Rua Siqueira Campos', 'Parque Industrial', 'São José do Rio Preto', 'SP', '2015-07-23 21:30:10', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
