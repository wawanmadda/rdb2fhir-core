DROP TABLE IF EXISTS `pasien`;

CREATE TABLE `pasien` (
                          `no_rkm_medis` varchar(15) NOT NULL,
                          `nm_pasien` varchar(40) DEFAULT NULL,
                          `no_ktp` varchar(20) DEFAULT NULL,
                          `jk` enum('L','P') DEFAULT NULL,
                          `tmp_lahir` varchar(15) DEFAULT NULL,
                          `tgl_lahir` date DEFAULT NULL,
                          `nm_ibu` varchar(40) NOT NULL,
                          `alamat` varchar(200) DEFAULT NULL,
                          `gol_darah` enum('A','B','O','AB','-') DEFAULT NULL,
                          `pekerjaan` varchar(60) DEFAULT NULL,
                          `stts_nikah` enum('BELUM MENIKAH','MENIKAH','JANDA','DUDHA','JOMBLO') DEFAULT NULL,
                          `agama` varchar(12) DEFAULT NULL,
                          `tgl_daftar` date DEFAULT NULL,
                          `no_tlp` varchar(40) DEFAULT NULL,
                          `umur` varchar(30) NOT NULL,
                          `pnd` enum('TS','TK','SD','SMP','SMA','SLTA/SEDERAJAT','D1','D2','D3','D4','S1','S2','S3','-') NOT NULL,
                          `keluarga` enum('AYAH','IBU','ISTRI','SUAMI','SAUDARA','ANAK','DIRI SENDIRI','LAIN-LAIN') DEFAULT NULL,
                          `namakeluarga` varchar(50) NOT NULL,
                          `kd_pj` char(3) NOT NULL,
                          `no_peserta` varchar(25) DEFAULT NULL,
                          `kd_kel` int NOT NULL,
                          `kd_kec` int NOT NULL,
                          `kd_kab` int NOT NULL,
                          `pekerjaanpj` varchar(35) NOT NULL,
                          `alamatpj` varchar(100) NOT NULL,
                          `kelurahanpj` varchar(60) NOT NULL,
                          `kecamatanpj` varchar(60) NOT NULL,
                          `kabupatenpj` varchar(60) NOT NULL,
                          `perusahaan_pasien` varchar(8) NOT NULL,
                          `suku_bangsa` int NOT NULL,
                          `bahasa_pasien` int NOT NULL,
                          `cacat_fisik` int NOT NULL,
                          `email` varchar(50) NOT NULL,
                          `nip` varchar(30) NOT NULL,
                          `kd_prop` int NOT NULL,
                          `propinsipj` varchar(30) NOT NULL,
                          PRIMARY KEY (`no_rkm_medis`)
);

INSERT INTO `pasien` VALUES ('-','-','-','L','-','2022-08-03','-','','-','-','MENIKAH','ISLAM','2022-08-03','-','0 Th 9 Bl 20 Hr','-','SAUDARA','-','-','-',1,1,1,'-','ALAMAT','KELURAHAN','KECAMATAN','KABUPATEN','-',1,1,1,'-','-',1,'PROPINSI'),('000002','DEWI EKAWATI','9201394901000008','P','-','2022-06-28','-','ALAMAT','-','-','MENIKAH','ISLAM','2022-06-28','08123442332','1 Th 2 Bl 24 Hr','-','SAUDARA','-','-','-',205,636,394,'-','ALAMAT','KELURAHAN','KECAMATAN','KABUPATEN','-',1,1,1,'-','-',3,'PROPINSI'),('000003','HAFIZ HARIYADI','9104224509000003','L','PATI','1997-05-22','JUMINTEN','-','-','DIREKTUR RS','MENIKAH','ISLAM','2022-07-14','085755655894','26 Th 3 Bl 25 Hr','S3','AYAH','PAIJO','A09','0003088762907',204,637,369,'DIREKTUR TAMBAN','-','SAMBILAWANG','TRANGKIL','KABUPATEN PATI','-',16,3,1,'-','-',3,'JAWA TENGAH'),('000004','DEPIYANTO','9104224606000005','L','-','1987-07-20','-','ALAMAT','-','-','MENIKAH','ISLAM','2022-07-20','085642552058','36 Th 2 Bl 1 Hr','-','SAUDARA','-','-','-',1,1,1,'-','ALAMAT','KELURAHAN','KECAMATAN','KABUPATEN','-',1,1,1,'-','-',1,'PROPINSI'),('000005','ROBY ALAMSYAH','9201076001000007','L','-','1986-07-20','-','ALAMAT','-','-','MENIKAH','ISLAM','2022-07-20','0858660612459','36 Th 10 Bl 21 Hr','-','SAUDARA','-','-','-',203,97,401,'-','ALAMAT','SANGUP','MUSUK','KABUPATEN BOYOLALI','-',1,1,1,'-','-',3,'JAWA TENGAH'),('000006','SAKHA HAMIZAN AQILA','9201076407000009','L','-','2017-08-03','DEWI EKAWATI','PAJANGAN BANTUL','-','-','BELUM MENIKAH','-','2022-08-03','081575751220','5 Th 9 Bl 26 Hr','-','AYAH','WINDIARTO','-','-',1,1,1,'-','PAJANGAN BANTUL','-','-','-','-',1,1,1,'-','-',1,'-'),('000008','1212','2','L','2','2022-12-28','2','ALAMAT','-','2','MENIKAH','ISLAM','2022-12-28','2','0 Th 2 Bl 12 Hr','-','SAUDARA','2','-','2',202,681,413,'2','ALAMAT','KELURAHAN','KECAMATAN','KABUPATEN','-',9,10,4,'2','-',497,'PROPINSI'),('000009','WAHYUDI KURNIAWAN','-','L','-','1990-01-01','-','PEKALONGAN','-','-','MENIKAH','-','2022-12-29','083875000083','33 Th 8 Bl 20 Hr','-','SAUDARA','-','-','-',1,1,1,'-','-','-','-','-','-',1,1,1,'adminadmin.com','-',1,'-'),('000010','GATOT SATRIYO','-','L','-','1990-01-01','-','PEKALONGAN','-','-','MENIKAH','-','2022-12-29','081271017549','33 Th 4 Bl 22 Hr','-','SAUDARA','-','-','-',1,1,1,'-','-','-','-','-','-',1,1,1,'adminadmin.com','-',1,'-'),('000011','SETIYAWAN KRISTANTO','0976565436789','L','MALANG','1990-02-21','JUMINTEN','JL. DOKTER CIPTI RT 01/RW01','A','GURU','MENIKAH','ISLAM','2023-01-18','0987676657','33 Th 5 Bl 14 Hr','-','SAUDARA','-','A09','-',201,693,425,'-','JL. DOKTER CIPTI RT 01/RW01','BEDALI','LAWANG','KABUPATEN MALANG','-',16,5,1,'-','-',267,'JAWA TIMUR'),('000013','PARAMITA RAMADANI','-','L','-','1990-01-01','-','BANTUL','-','-','MENIKAH','-','2023-04-11','0812345678','33 Th 8 Bl 19 Hr','-','SAUDARA','-','-','-',1053,190,232,'-','-','-','-','-','-',1,1,1,'testes.yahoo.com','-',300,'JAWA TENGAHJL ANGGUR NO.36 RT'),('000014','BY. NYONYA RAHMI','-','L','-','2023-05-14','RAHMI','GRESIK','-','-','BELUM MENIKAH','-','2023-05-14','0','0 Th 0 Bl 19 Hr','-','AYAH','WAWAN','-','-',1,1,1,'-','GRESIK','-','-','-','-',1,1,1,'-','-',1,'-'),('000019','RIZKI AMALIA','-','L','PURWOKERTO','1994-06-22','WAGINEM','-','-','-','MENIKAH','ISLAM','2023-05-17','-','29 Th 1 Bl 28 Hr','S1','SAUDARA','-','A09','-',1101010214,717,449,'-','-','SUAK LAMATAN','TEUPAH SELATAN','KABUPATEN SIMEULUE','-',5,11,5,'-','-',536,'ACEH'),('000020','sasas','-','L','-','2023-05-29','asas','asas','-','-','BELUM MENIKAH','-','2023-05-29','0','0','-','AYAH','sasas','-','-',1,1,1,'-','asas','-','-','-','-',1,1,1,'-','-',1,'-'),('000021','TES PASIEN','-','L','KLATEN','1985-05-22','JUMINTEN','-','-','-','MENIKAH','ISLAM','2023-07-08','-','38 Th 1 Bl 16 Hr','-','DIRI SENDIRI','TES','BPJ','-',1101010220,723,455,'TES','-','TES','SINGKIL','KABUPATEN ACEH SINGKIL','-',15,11,3,'-','-',536,'ACEH'),('000022','RUDI SANTOSO','3374135702570001','P','KEBUMEN','1957-03-11','JUMINTEN','TES','-','-','MENIKAH','ISLAM','2023-08-17','123123213','66 Th 6 Bl 5 Hr','-','SAUDARA','-','BPJ','0002084509113',1101010222,725,457,'-','TES','KEDUNGWARU','PREMBUN','KABUPATEN KEBUMEN','-',1,1,1,'-','-',3,'JAWA TENGAH'),('1303','WIWIK LESTARI','-','L','KARANGANYAR','1983-01-27','-','RT 01 RW 01','-','-','MENIKAH','ISLAM','2023-01-27','081314188603','40 Th 5 Bl 23 Hr','S2','SAUDARA','-','A09','-',200,694,426,'-','RT 01 RW 01','NGIPAK','KARANGMOJO','KABUPATEN GUNUNG KIDUL','-',1,11,1,'-','-',469,'DI YOGYAKARTA'),('1402','HASRUN OGANDA','9104223107000004','L','KLATEN','1988-02-02','-','123','-','-','MENIKAH','ISLAM','2022-06-26','082298008985','35 Th 3 Bl 23 Hr','-','SAUDARA','-','-','0000131320833',1441,500,387,'-','-','TELUK SASAH','SERI KUALA LOBAM','KABUPATEN BINTAN','-',1,1,1,'-','-',469,'KEPULAUAN RIAU');

DROP TABLE IF EXISTS `pasien_mati`;
CREATE TABLE `pasien_mati` (
                               `tanggal` date DEFAULT NULL,
                               `jam` time DEFAULT NULL,
                               `no_rkm_medis` varchar(15) NOT NULL DEFAULT '',
                               `keterangan` varchar(100) DEFAULT NULL,
                               `temp_meninggal` enum('-','Rumah Sakit','Puskesmas','Rumah Bersalin','Rumah Tempat Tinggal','Lain-lain (Termasuk Doa)','Tidak tahu') DEFAULT NULL,
                               `icd1` varchar(20) DEFAULT NULL,
                               `icd2` varchar(20) DEFAULT NULL,
                               `icd3` varchar(20) DEFAULT NULL,
                               `icd4` varchar(20) DEFAULT NULL,
                               `kd_dokter` varchar(20) NOT NULL,
                               PRIMARY KEY (`no_rkm_medis`)
);
INSERT INTO `pasien_mati` VALUES ('2023-05-29','00:00:00','000004','1','-','999','1','1','1','D0000004');
