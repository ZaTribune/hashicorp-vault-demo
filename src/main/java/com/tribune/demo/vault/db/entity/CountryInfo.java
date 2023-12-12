package com.tribune.demo.vault.db.entity;

/**
 * Interface-based Projections
 * The easiest way to limit the result of the queries to
 * only the name attributes is by declaring an interface
 * that exposes accessor methods for the properties to be read.
 * Projection for {@link Country}
 */
public interface CountryInfo {
    String getName();

    String getDescription();
}
