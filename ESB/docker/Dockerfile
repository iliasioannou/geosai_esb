FROM planetek/ubuntu-maven-git:base
LABEL maintainer.name="ilias ioannou" \
      maintainer.email="ioannou@planetek.gr"

ARG branch

WORKDIR /
RUN git clone -b $branch https://teamEreticoTfs:vAsK*AIJFB@tfs.planetek.it/Planetek%20Hellas/pkh111_EOSAI/_git/pkh111_EOSAI_ESB /src

WORKDIR src/ESB/water_quality
RUN cp -r src/main/resources/legenda /legenda
RUN mvn clean install
WORKDIR target
RUN rm -r */
RUN cp water_quality-1.0.0-SNAPSHOT.zip /opt/mule-standalone/apps
WORKDIR /opt/mule-standalone/bin
CMD ["./mule"]
