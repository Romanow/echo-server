[![CI](https://github.com/Romanow/echo-server/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/Romanow/echo-server/actions/workflows/build.yml)
[![pre-commit](https://img.shields.io/badge/pre--commit-enabled-brightgreen?logo=pre-commit)](https://github.com/pre-commit/pre-commit)
[![Release](https://img.shields.io/github/v/release/Romanow/echo-server?logo=github&sort=semver)](https://github.com/Romanow/echo-server/releases/latest)
[![Echo Server](https://img.shields.io/docker/pulls/romanowalex/echo-server?logo=docker)](https://hub.docker.com/r/romanowalex/echo-server)
[![License](https://img.shields.io/github/license/Romanow/echo-server)](https://github.com/Romanow/echo-server/blob/master/LICENSE)

# Echo Server

GitHub: [romanow/echo-server](https://github.com/Romanow/echo-server).

## Локальный запуск

Используем [docker-compose.yml](docker-compose.yml)

```shell
$ docker compose up -d --wait
$ curl 'http://localhost:8080?message=Hello,%20World'
> Hello, world
```
