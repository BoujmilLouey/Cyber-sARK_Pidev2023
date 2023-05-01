<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230305181556 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67D79F37AE5');
        $this->addSql('DROP INDEX IDX_6EEAA67D79F37AE5 ON commande');
        $this->addSql('ALTER TABLE commande DROP reference, CHANGE id_user_id user_id INT DEFAULT NULL, CHANGE date date_commande DATE NOT NULL, CHANGE montant montant_commande DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67DA76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('CREATE INDEX IDX_6EEAA67DA76ED395 ON commande (user_id)');
        $this->addSql('ALTER TABLE ligne_commande DROP FOREIGN KEY FK_3170B74B9AF8E3A3');
        $this->addSql('DROP INDEX UNIQ_3170B74B9AF8E3A3 ON ligne_commande');
        $this->addSql('ALTER TABLE ligne_commande ADD commande_id INT DEFAULT NULL, ADD price DOUBLE PRECISION NOT NULL, DROP reference, CHANGE id_commande_id produit_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE ligne_commande ADD CONSTRAINT FK_3170B74BF347EFB FOREIGN KEY (produit_id) REFERENCES produit (id)');
        $this->addSql('ALTER TABLE ligne_commande ADD CONSTRAINT FK_3170B74B82EA2E54 FOREIGN KEY (commande_id) REFERENCES commande (id)');
        $this->addSql('CREATE INDEX IDX_3170B74BF347EFB ON ligne_commande (produit_id)');
        $this->addSql('CREATE INDEX IDX_3170B74B82EA2E54 ON ligne_commande (commande_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67DA76ED395');
        $this->addSql('DROP INDEX IDX_6EEAA67DA76ED395 ON commande');
        $this->addSql('ALTER TABLE commande ADD reference VARCHAR(255) NOT NULL, CHANGE user_id id_user_id INT DEFAULT NULL, CHANGE date_commande date DATE NOT NULL, CHANGE montant_commande montant DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67D79F37AE5 FOREIGN KEY (id_user_id) REFERENCES user (id)');
        $this->addSql('CREATE INDEX IDX_6EEAA67D79F37AE5 ON commande (id_user_id)');
        $this->addSql('ALTER TABLE ligne_commande DROP FOREIGN KEY FK_3170B74BF347EFB');
        $this->addSql('ALTER TABLE ligne_commande DROP FOREIGN KEY FK_3170B74B82EA2E54');
        $this->addSql('DROP INDEX IDX_3170B74BF347EFB ON ligne_commande');
        $this->addSql('DROP INDEX IDX_3170B74B82EA2E54 ON ligne_commande');
        $this->addSql('ALTER TABLE ligne_commande ADD id_commande_id INT DEFAULT NULL, ADD reference VARCHAR(255) NOT NULL, DROP produit_id, DROP commande_id, DROP price');
        $this->addSql('ALTER TABLE ligne_commande ADD CONSTRAINT FK_3170B74B9AF8E3A3 FOREIGN KEY (id_commande_id) REFERENCES commande (id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_3170B74B9AF8E3A3 ON ligne_commande (id_commande_id)');
    }
}
