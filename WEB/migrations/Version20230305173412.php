<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230305173412 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE game (id INT AUTO_INCREMENT NOT NULL, game_category_id INT DEFAULT NULL, game_categorie_id INT DEFAULT NULL, name VARCHAR(255) NOT NULL, description VARCHAR(255) NOT NULL, image VARCHAR(500) DEFAULT NULL, INDEX IDX_232B318CCC13DFE0 (game_category_id), INDEX IDX_232B318C5225D343 (game_categorie_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE game_category (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE game_rating (id INT AUTO_INCREMENT NOT NULL, rating INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE scores (id INT AUTO_INCREMENT NOT NULL, game_id_id INT NOT NULL, user_id_id INT NOT NULL, score INT NOT NULL, created_at DATETIME NOT NULL, INDEX IDX_750375E4D77E7D8 (game_id_id), INDEX IDX_750375E9D86650F (user_id_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE game ADD CONSTRAINT FK_232B318CCC13DFE0 FOREIGN KEY (game_category_id) REFERENCES game (id)');
        $this->addSql('ALTER TABLE game ADD CONSTRAINT FK_232B318C5225D343 FOREIGN KEY (game_categorie_id) REFERENCES game_category (id)');
        $this->addSql('ALTER TABLE scores ADD CONSTRAINT FK_750375E4D77E7D8 FOREIGN KEY (game_id_id) REFERENCES game (id)');
        $this->addSql('ALTER TABLE scores ADD CONSTRAINT FK_750375E9D86650F FOREIGN KEY (user_id_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE user CHANGE is_verified is_verified TINYINT(1) NOT NULL, CHANGE is_banned is_banned TINYINT(1) NOT NULL, CHANGE image image VARCHAR(255) NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE game DROP FOREIGN KEY FK_232B318CCC13DFE0');
        $this->addSql('ALTER TABLE game DROP FOREIGN KEY FK_232B318C5225D343');
        $this->addSql('ALTER TABLE scores DROP FOREIGN KEY FK_750375E4D77E7D8');
        $this->addSql('ALTER TABLE scores DROP FOREIGN KEY FK_750375E9D86650F');
        $this->addSql('DROP TABLE game');
        $this->addSql('DROP TABLE game_category');
        $this->addSql('DROP TABLE game_rating');
        $this->addSql('DROP TABLE scores');
        $this->addSql('ALTER TABLE user CHANGE is_verified is_verified TINYINT(1) DEFAULT NULL, CHANGE is_banned is_banned TINYINT(1) DEFAULT NULL, CHANGE image image VARCHAR(255) DEFAULT NULL');
    }
}
