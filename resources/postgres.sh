docker run \
 -d --name postgres \
 -p 5433:5432 \
 -e POSTGRES_DB=test \
 -e POSTGRES_USER=postgres \
 -e POSTGRES_PASSWORD=postgres \
 -v /Users/pabloramosizquierdo/Desktop/Pablo/Tecnologias/Dockers/PostgreSQL/data:/var/lib/postgresql/data \
 postgres:14.13