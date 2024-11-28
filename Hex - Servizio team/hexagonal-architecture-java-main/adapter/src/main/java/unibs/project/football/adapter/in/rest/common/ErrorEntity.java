package unibs.project.football.adapter.in.rest.common;

/** An error entity with a status and message returned via REST API in case of an error. */
public record ErrorEntity(int httpStatus, String errorMessage) {}
