FROM dockerhub.planetek.it/pkh111_eosai_esb:base
LABEL maintainer.name="ilias ioannou" \
      maintainer.email="ioannou@planetek.gr"

ARG branch
RUN git checkout $branch
RUN git pull origin $branch

WORKDIR water_quality
RUN mvn clean install
WORKDIR target
RUN rm -r */
RUN cp water_quality-1.0.0-SNAPSHOT.zip /opt/mule-standalone/apps
WORKDIR /opt/mule-standalone/bin
CMD ["./mule"]
