package dk.sdu.swe.data.strategies;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class NameStrategy extends PhysicalNamingStrategyStandardImpl {
    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return super.toPhysicalCatalogName(toSnakeCase(name), jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return super.toPhysicalSchemaName(toSnakeCase(name), jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return super.toPhysicalTableName(toSnakeCase(name), jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return super.toPhysicalSequenceName(toSnakeCase(name), jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return super.toPhysicalColumnName(toSnakeCase(name), jdbcEnvironment);
    }

    private Identifier toSnakeCase(Identifier id) {
        if (id == null)
            return id;

        String name = id.getText();
        String snakeName = name.replaceAll("([a-z]+)([A-Z]+)", "$1\\_$2").toLowerCase();
        if (!snakeName.equals(name))
            return new Identifier(snakeName, id.isQuoted());
        else
            return id;
    }
}
