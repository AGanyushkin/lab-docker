FROM nvidia/cuda:10.2-cudnn7-devel-ubuntu18.04

ARG PYTHON_VERSION=3.8

ARG PYTORCH_VERSION=1.8.1
ARG CUDA_VERSION=10.2
ARG CUPY=cuda102

ENV TZ America/New_York
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apt-get update && apt-get install -y \
    locales \
    tzdata \
    curl \
    ca-certificates \
    bzip2 \
    unzip \
    unrar \
    libmagic1 \
    build-essential \
    git \
    ffmpeg \
    libsm6 \
    libxext6

RUN curl -o ~/miniconda.sh https://repo.anaconda.com/miniconda/Miniconda3-latest-Linux-x86_64.sh && \
     chmod +x ~/miniconda.sh && \
     ~/miniconda.sh -b -p /opt/conda && \
     rm ~/miniconda.sh && \
     /opt/conda/bin/conda install -y python=$PYTHON_VERSION numpy pyyaml scipy ipython mkl mkl-include ninja cython typing && \
     /opt/conda/bin/conda install -y -c pytorch pytorch=${PYTORCH_VERSION} cudatoolkit=${CUDA_VERSION} && \
     /opt/conda/bin/conda install -y -c bioconda -c conda-forge snakemake && \
     /opt/conda/bin/conda clean -ya

ENV PATH /opt/conda/bin:$PATH

RUN conda install \
 simplejson \
 ijson \
 unidecode \
 psutil \
 requests \
 mmh3 \
 lxml \
 jsonpath-ng \
 pycountry \
 fuzzywuzzy \
 python-levenshtein \
 regex \
 jupyter \
 matplotlib \
 pandas \
 typer \
 aiohttp \
 aiohttp-jinja2 \
  -c conda-forge 

RUN /opt/conda/bin/conda clean -ya

RUN pip install -U pip

ARG PYTORCH_LIGHTNING_VERSION=1.4.5
RUN pip install pytorch-lightning==${PYTORCH_LIGHTNING_VERSION} && rm -rf /root/.cache


RUN locale-gen en_US && locale-gen en_US.UTF-8 && update-locale
ENV LANG=en_US.UTF-8
ENV LC_ALL=en_US.UTF-8


# entrypoint
RUN echo '#!/bin/bash \n\n\
jupyter notebook --allow-root --ip 0.0.0.0\
' > /usr/bin/entrypoint.sh \
&& \
chmod +x /usr/bin/entrypoint.sh


WORKDIR /host
ENTRYPOINT ["/usr/bin/entrypoint.sh"]
