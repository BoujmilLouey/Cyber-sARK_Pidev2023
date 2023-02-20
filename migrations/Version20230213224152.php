<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230213224152 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE classement (id INT AUTO_INCREMENT NOT NULL, id_user_id INT DEFAULT NULL, id_jeux_id INT DEFAULT NULL, score DOUBLE PRECISION NOT NULL, UNIQUE INDEX UNIQ_55EE9D6D79F37AE5 (id_user_id), UNIQUE INDEX UNIQ_55EE9D6D32B700A2 (id_jeux_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE jeux (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, image VARCHAR(255) NOT NULL, categorie VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE classement ADD CONSTRAINT FK_55EE9D6D79F37AE5 FOREIGN KEY (id_user_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE classement ADD CONSTRAINT FK_55EE9D6D32B700A2 FOREIGN KEY (id_jeux_id) REFERENCES jeux (id)');
        $this->addSql('ALTER TABLE game ADD game_categorie_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE game ADD CONSTRAINT FK_232B318C5225D343 FOREIGN KEY (game_categorie_id) REFERENCES game_category (id)');
        $this->addSql('CREATE INDEX IDX_232B318C5225D343 ON game (game_categorie_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE classement DROP FOREIGN KEY FK_55EE9D6D79F37AE5');
        $this->addSql('ALTER TABLE classement DROP FOREIGN KEY FK_55EE9D6D32B700A2');
        $this->addSql('DROP TABLE classement');
        $this->addSql('DROP TABLE jeux');
        $this->addSql('ALTER TABLE game DROP FOREIGN KEY FK_232B318C5225D343');
        $this->addSql('DROP INDEX IDX_232B318C5225D343 ON game');
        $this->addSql('ALTER TABLE game DROP game_categorie_id');
    }
}
