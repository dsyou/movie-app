package pl.dsyou.movierating.common.handler;

public interface CmdHandler <T> {
    void handle(T command);
}
