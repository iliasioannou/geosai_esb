# Launch in development mode

- Build the dev image
  
    mv docker
    ./build-dev.sh

- Move in the project folder (where *pom.xml* file is located) and 

    mvn clean install && docker run --name eosai_esb -p 9190:9190 --net eosai -ti --rm -v /home/<LOCAL USER>/.../<LOCAL REPO>pkh111_EOSAI_dockerized/shared_data/:/shared -v `pwd`:/root planetek/eosai_esb:dev bash

- Once in:
    
    ./dev.sh

# Launch in production mode

- Build the final image
  
    ./build.sh


