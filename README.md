# Launch in development mode

- Build the dev image
  
    mv docker
    ./build-dev.sh

- Move in the project folder (where *pom.xml* file is located) and 

    mvn clean install && docker run --name cmems_esb -p 9190:9190 --net cmems -ti --rm -v /home/francesco/cmems/pkz029_UU_CMEMS_dockerized/shared_data/:/shared -v `pwd`:/root planetek/cmems_esb:dev bash

- Once in:
    
    ./dev.sh

