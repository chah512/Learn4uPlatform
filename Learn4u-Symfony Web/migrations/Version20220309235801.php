<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220309235801 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE reclamation_user');
        $this->addSql('ALTER TABLE events_reservation ADD user_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE events_reservation ADD CONSTRAINT FK_D8A20356A76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('CREATE INDEX IDX_D8A20356A76ED395 ON events_reservation (user_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE reclamation_user (reclamation_id INT NOT NULL, user_id INT NOT NULL, INDEX IDX_8CDC5126A76ED395 (user_id), INDEX IDX_8CDC51262D6BA2D9 (reclamation_id), PRIMARY KEY(reclamation_id, user_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE reclamation_user ADD CONSTRAINT FK_8CDC51262D6BA2D9 FOREIGN KEY (reclamation_id) REFERENCES reclamation (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reclamation_user ADD CONSTRAINT FK_8CDC5126A76ED395 FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE events_reservation DROP FOREIGN KEY FK_D8A20356A76ED395');
        $this->addSql('DROP INDEX IDX_D8A20356A76ED395 ON events_reservation');
        $this->addSql('ALTER TABLE events_reservation DROP user_id');
    }
}
