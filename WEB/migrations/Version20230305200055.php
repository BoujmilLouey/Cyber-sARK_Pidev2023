<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230305200055 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE calendar (id INT AUTO_INCREMENT NOT NULL, title VARCHAR(255) NOT NULL, start DATETIME NOT NULL, end DATETIME NOT NULL, all_day TINYINT(1) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BC7ECF78B0');
        $this->addSql('DROP INDEX IDX_67F068BC7ECF78B0 ON commentaire');
        $this->addSql('ALTER TABLE commentaire ADD note INT NOT NULL, DROP cours_id, CHANGE contenue contenu VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE cours DROP note, DROP video, DROP pdf');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE calendar');
        $this->addSql('ALTER TABLE commentaire ADD cours_id INT DEFAULT NULL, DROP note, CHANGE contenu contenue VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BC7ECF78B0 FOREIGN KEY (cours_id) REFERENCES cours (id)');
        $this->addSql('CREATE INDEX IDX_67F068BC7ECF78B0 ON commentaire (cours_id)');
        $this->addSql('ALTER TABLE cours ADD note INT NOT NULL, ADD video VARCHAR(255) NOT NULL, ADD pdf VARCHAR(255) NOT NULL');
    }
}
